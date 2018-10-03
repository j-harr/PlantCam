package com.plantcam.jacob.plantcam;

import android.bluetooth.BluetoothClass;
import android.os.Handler;
import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class FindDevices implements Callable<Object> {
    private int portNum;
    private List<Device> devices;
    private DatagramSocket s;
    private int msg_length;
    private Handler handler;

    public FindDevices(int port, int msg_length) {
        //System.out.println("Find Devices COnstructor");
        portNum = port;
        if(msg_length <= 0)
            this.msg_length = 100;
        else this.msg_length = msg_length;
    }

    public FindDevices(Handler h, int port) {
        handler = h;
        portNum = port;
        msg_length = 100;
    }

    @Override
    public List<Device> call() throws Exception {
        devices = new ArrayList<Device>();
        Log.w("Find Devices", "Creating datagram socket");
        s = new DatagramSocket(portNum);
        Log.w("Find Devices", "Setting socket timeout");
        s.setSoTimeout(Config.findDeviceTimeout);
        ArrayList<String> addresses = new ArrayList<String>();
        Log.w("Find Devices", "Creating receive buffer");


        DatagramPacket packet;
        int repeats = 0;

        /* Main Loop - packets from discoverable devices received */
        while(repeats < 4) {
            Log.w("Find Devices", "Top of the loop");
            try {
                byte[] recvBuffer = new byte[msg_length];
                Log.w("Find Devices", "Creating packet");
                packet = new DatagramPacket(recvBuffer, msg_length);
                Log.w("Find Devices", "Receiving Packet");
                s.receive(packet);
                Log.w("Find Devices", "Packet Received");
                String receiveStr = new String(recvBuffer).replaceAll("\u0000.*", "");
                String address = receiveStr.substring(0, receiveStr.indexOf("="));
                String hostname = receiveStr.substring(receiveStr.indexOf("=") + 1);

                if(addresses.contains(address) == false) {
                    //repeats = 0;
                    Log.w("Find Devices", hostname);
                    devices.add(new Device(hostname, address));
                    addresses.add(address);
                } else {
                    repeats++;
                }
            } catch(SocketTimeoutException e) {
                Log.w("Find Devices",devices.size() + " devices found after "
                        + Config.findDeviceTimeout + "ms.");
                break;
            }
        }
        s.close();
        handler.sendEmptyMessage(0);
        return devices;
    }
}

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
	
	public FindDevices(int port, int msg_length) {
		System.out.println("Find Devices COnstructor");
		portNum = port;
		if(msg_length <= 0)
			this.msg_length = 100;
		else this.msg_length = msg_length;
	}
	
	public FindDevices(int port) {
		portNum = port;
		msg_length = 100;
	}

	@Override
	public List<Device> call() throws Exception {
		devices = new ArrayList<Device>();
		s = new DatagramSocket(portNum);
		s.setSoTimeout(Config.findDeviceTimeout);
		ArrayList<String> addresses = new ArrayList<String>();
		byte[] recvBuffer = new byte[msg_length];
		DatagramPacket packet;
		
		/* Main Loop - packets from discoverable devices received */
		while(true) {
			try {
			packet = new DatagramPacket(recvBuffer, msg_length);
			s.receive(packet);
			String receiveStr = new String(recvBuffer);
			String address = receiveStr.substring(0, receiveStr.indexOf("="));
			String hostname = receiveStr.substring(receiveStr.indexOf("="));
			
			if(addresses.contains(address) == false) {
				System.out.println(hostname);
				devices.add(new Device(hostname, address));
			}
			} catch(SocketTimeoutException e) {
				System.out.println(devices.size() + " devices found after " 
						+ Config.findDeviceTimeout + "ms.");
				break;
			}
		}
		return devices;
	}
	
}

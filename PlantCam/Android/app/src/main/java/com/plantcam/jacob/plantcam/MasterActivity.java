package com.plantcam.jacob.plantcam;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MasterActivity extends AppCompatActivity {
    private Button mfindDevicesButton;
    private Button mTakePictureButton;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Device> deviceList = new ArrayList<Device>();

    private RecyclerView logView;
    private RecyclerView.Adapter logadapter;
    private RecyclerView.LayoutManager logLayoutManager;
    private List<String> outputLog = new LinkedList<String>();
    private Thread t_df;

    /* Variables for Find Devices */
    private DeviceGroup deviceGroup;
    Callable<Object> deviceFinder;
    FutureTask<Object> deviceFindTask;

    private boolean isFindingDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        isFindingDevices = false;



        /* Device List Recycler View */
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DeviceAdapter(deviceList, getApplicationContext());
        recyclerView.setAdapter(adapter);

        /* Output Log Recycler View */
        logView = (RecyclerView) findViewById(R.id.log_view);
        logLayoutManager = new LinearLayoutManager(this);
        logView.setLayoutManager(logLayoutManager);
        logadapter = new LogAdapter(outputLog, getApplicationContext());
        logView.setAdapter(logadapter);

        outputToMasterLog("Press 'Find Devices' to search for devices.");

        mfindDevicesButton = (Button) findViewById(R.id.findDevicesButton);
        mTakePictureButton = (Button) findViewById(R.id.takePicture);
        mTakePictureButton.setVisibility(View.INVISIBLE);

        mTakePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                takePicture();
            }
        });

        mfindDevicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                findDevices();
            }
        });
    }

    private Handler doneFindingDevices = new Handler(){
        @Override
        public void handleMessage(Message m){
            Log.w("Handler", "Done determining stuff");
            List<Device> tempList = new ArrayList<Device>();
            try {
                if(deviceFindTask.isDone()){
                    tempList = (List<Device>) deviceFindTask.get();
                } else if(deviceFindTask.isCancelled()){
                    outputToMasterLog("Find Devices task cancelled.");
                }
            } catch (InterruptedException ie){

            } catch (ExecutionException ee){

            }
            deviceList.clear();
            for(Device d : tempList){
                deviceList.add(d);
            }
            if(deviceList.size() != 0){
                mTakePictureButton.setVisibility(View.VISIBLE);
                outputToMasterLog("0 devices were found on this network.");
            } else{
                mTakePictureButton.setVisibility(View.INVISIBLE);
                outputToMasterLog(deviceList.size() + " devices were found on this network.");
            }
            deviceGroup = new DeviceGroup(deviceList);
            Log.w("Find Devices", "Device list size: " + deviceList.size());
            adapter.notifyDataSetChanged();
            isFindingDevices = false;
        }
    };

    private void findDevices(){
        outputToMasterLog(isFindingDevices == true ? "true" : "false");
        if(isFindingDevices){
            outputToMasterLog("Currently looking for devices...");
        } else {
            outputToMasterLog("Searching for devices...");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            deviceList.clear();
            try {
                outputToMasterLog("This phone's address is: " + Broadcast.getLocalHost());
                deviceFinder = new FindDevices(doneFindingDevices, Config.discoveryPort);
                deviceFindTask = new FutureTask<>(deviceFinder);
                t_df = new Thread(deviceFindTask);
                t_df.start();
                isFindingDevices = true;
            } catch (Exception e) {
                deviceList.add(new Device("security", "exception"));
                Log.w("Error", e.getMessage());
            }
        }
    }

    private void takePicture(){
        deviceGroup.send("from Android");
    }

    protected void outputToMasterLog(String message){
        outputLog.add(0, message);
        Log.w("Output log","Outputing to master log");
        logadapter.notifyDataSetChanged();
    }
}

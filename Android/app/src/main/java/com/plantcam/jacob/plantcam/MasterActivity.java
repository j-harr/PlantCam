package com.plantcam.jacob.plantcam;

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
import java.util.List;

public class MasterActivity extends AppCompatActivity {
    private Button mfindDevicesButton;
    private Button mTakePictureButton;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Device> deviceList = new ArrayList<Device>();
    private List<String> outputLog = new ArrayList<String>();

    /* Variables for Find Devices */
    private DeviceGroup deviceGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        /* Device List Recycler View */
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DeviceAdapter(deviceList, getApplicationContext());
        recyclerView.setAdapter(adapter);

        /* Output Log Recycler View */

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

    private void findDevices(){
        FindDevices deviceFinder = new FindDevices(Config.discoveryPort);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        deviceList.clear();
        try {
            List<Device> tempList = deviceFinder.call();
            deviceList.clear();
            for(Device d : tempList){
                deviceList.add(d);
            }
            if(deviceList.size() != 0){
                mTakePictureButton.setVisibility(View.VISIBLE);
            } else{
                mTakePictureButton.setVisibility(View.INVISIBLE);
            }
            deviceGroup = new DeviceGroup(deviceList);
        } catch(Exception e){
            deviceList.add(new Device("security", "exception"));
            Log.w("Error" , e.getMessage());
        }
        Log.w("Find Devices", "Device list size: " + deviceList.size());
        adapter.notifyDataSetChanged();
    }

    private void takePicture(){
        deviceGroup.send("from Android");
    }
}

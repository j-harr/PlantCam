package com.plantcam.jacob.plantcam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {

    List<Device> deviceList;

    public DeviceAdapter(List<Device> deviceList, Context context){
        this.deviceList = deviceList;
    }

    @Override
    public DeviceAdapter.DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_row, parent, false);
        DeviceViewHolder viewHolder = new DeviceViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DeviceAdapter.DeviceViewHolder holder, int position) {
       /* holder.text.setText(deviceList.get(position).toString()); */
       holder.hostname.setText(deviceList.get(position).name());
       holder.address.setText(deviceList.get(position).address());
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public static class DeviceViewHolder extends RecyclerView.ViewHolder {

       /* protected TextView image;
        protected TextView text;*/
        protected TextView hostname;
        protected TextView address;

        public DeviceViewHolder(View itemView){
            super(itemView);
            /*text = (TextView) itemView.findViewById(R.id.text_id);*/
            hostname = (TextView) itemView.findViewById(R.id.device_id);
            address = (TextView) itemView.findViewById(R.id.address_id);
        }
    }
}

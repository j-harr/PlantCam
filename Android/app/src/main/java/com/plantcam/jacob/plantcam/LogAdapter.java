package com.plantcam.jacob.plantcam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {

    List<String> logList;

    public LogAdapter(List<String> logList, Context context){
        this.logList = logList;
    }

    @Override
    public LogAdapter.LogViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_row, parent, false);
        LogViewHolder viewHolder = new LogViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LogAdapter.LogViewHolder holder, int position) {
        /* holder.text.setText(deviceList.get(position).toString()); */
        holder.text.setText(logList.get(position));
    }

    @Override
    public int getItemCount() {
        return logList.size();
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder {

         protected TextView text;

        public LogViewHolder(View itemView){
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.log_row_id);
        }
    }
}

package com.academicbot.academicbot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {
    List<String> list_data;
    Context context;
    String string;

    public  AttendanceAdapter(Context context,List<String> list){

        this.context=context;
        this.list_data=list;
    }

    @NonNull
    @Override
    public AttendanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View attendance_list=layoutInflater.inflate(R.layout.marks_list_layout,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(attendance_list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceAdapter.ViewHolder viewHolder, int i) {
        final List<String> attendance_model=Collections.singletonList(list_data.get(i));
        viewHolder.text.setText(attendance_model.get(i));
        viewHolder.aSwitch.setTextOff("Present");
        viewHolder.aSwitch.setTextOn("Absent");
        if (viewHolder.aSwitch.isChecked()){
            string=viewHolder.aSwitch.getTextOn().toString();
        }
        else {
            string=viewHolder.aSwitch.getTextOn().toString();
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {

        Switch aSwitch;
        TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.aSwitch=itemView.findViewById(R.id.switch0);
            this.text=itemView.findViewById(R.id.text);
        }
    }
    }



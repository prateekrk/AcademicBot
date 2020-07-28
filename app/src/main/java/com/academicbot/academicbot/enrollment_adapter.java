package com.academicbot.academicbot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class enrollment_adapter extends RecyclerView.Adapter<enrollment_adapter.ViewHolder> {

    Context context;
    List<enrollment_model> list;
    public enrollment_adapter( Context context, List<enrollment_model> l){
        this.context=context;
        this.list=l;
    }
    public enrollment_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View enrollment=layoutInflater.inflate(R.layout.subject_code_layout,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(enrollment);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull enrollment_adapter.ViewHolder viewHolder, int i) {
        enrollment_model e=list.get(i);
        viewHolder.code.setText(e.getCode());
        viewHolder.credits.setText(e.getCredits());
        viewHolder.sub.setText(e.getSub());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {

            TextView code,sub,credits;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.credits=itemView.findViewById(R.id.textview1);
            this.sub=itemView.findViewById(R.id.textview2);
            this.code=itemView.findViewById(R.id.textview3);

        }
    }
}

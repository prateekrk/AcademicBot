package com.academicbot.academicbot;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class MyAssignmentAdapter extends RecyclerView.Adapter<MyAssignmentAdapter.ViewHolder> {

    Context context;
    List<assignmentModel> listdata;
    public MyAssignmentAdapter(Context context,List<assignmentModel> list_data){
        this.context=context;
        this.listdata=list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View assignmentlist=layoutInflater.inflate(R.layout.assignment_list_layout,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(assignmentlist);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        final assignmentModel mylistdata=listdata.get(i);
        viewHolder.due_date.setText(mylistdata.getDue_date());
        viewHolder.subjet.setText(mylistdata.getSubject());
        viewHolder.description.setText(mylistdata.getDescription());

    }



    @Override
    public int getItemCount() {
        return listdata.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
          TextView due_date,subjet,description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.due_date=itemView.findViewById(R.id.due_date);
            this.subjet=itemView.findViewById(R.id.subject);
            this.description=itemView.findViewById(R.id.assignment_desc);


        }
    }
}

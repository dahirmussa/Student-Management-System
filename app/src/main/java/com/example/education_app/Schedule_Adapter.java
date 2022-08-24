package com.example.education_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Schedule_Adapter extends RecyclerView.Adapter<Schedule_Adapter.myviewholder> {
    Context c;
    ArrayList<Schedule> list;

    public Schedule_Adapter(Context c, ArrayList<Schedule> list) {
        this.c = c;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.item,parent,false);
        return new myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        Schedule schedule = list.get(position);
        holder.day.setText(schedule.getDay());
        holder.modules.setText(schedule.getModule());
        holder.A_time.setText(schedule.getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        TextView day , modules, A_time;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            day = itemView.findViewById(R.id.days);
            modules = itemView.findViewById(R.id.module);
            A_time = itemView.findViewById(R.id.time);

        }
    }
}

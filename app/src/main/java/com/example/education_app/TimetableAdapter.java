package com.example.education_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.myviewholder>{
    Context c;
    ArrayList<Timetables> list;

    public TimetableAdapter(Context c, ArrayList<Timetables> list) {
        this.c = c;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.itemstimetable,parent,false);
        return new TimetableAdapter.myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        Timetables timetables = list.get(position);
        holder.Day.setText(timetables.getDay());
        holder.Module.setText(timetables.getModules());
        holder.Time.setText(timetables.getTime());
        holder.Rooms.setText(timetables.getRoom());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        TextView Day , Module, Time, Rooms;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            Day = itemView.findViewById(R.id.days);
            Module = itemView.findViewById(R.id.modules);
            Time = itemView.findViewById(R.id.time);
            Rooms = itemView.findViewById(R.id.Room);
        }
    }
}

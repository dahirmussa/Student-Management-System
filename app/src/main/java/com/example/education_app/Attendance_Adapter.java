package com.example.education_app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Attendance_Adapter extends RecyclerView.Adapter<Attendance_Adapter.MyViewHolder>
{
    private List<Attendance_List> attendance_lists;
    private final Context context;

    public Attendance_Adapter(List<Attendance_List> attendance_lists, Context context) {
        this.attendance_lists = attendance_lists;
        this.context = context;
    }

    @NonNull
    @Override
    public Attendance_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Attendance_Adapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

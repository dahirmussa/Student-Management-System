package com.example.education_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Module_Adapter extends RecyclerView.Adapter<Module_Adapter.myviewholder>
{
    Context c;
    ArrayList<workModule> list;

    public Module_Adapter(Context c, ArrayList<workModule> list) {
        this.c = c;
        this.list = list;
    }

    @NonNull
    @Override
    public Module_Adapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.modules_items,parent,false);
        return new Module_Adapter.myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Module_Adapter.myviewholder holder, int position) {

        workModule workModule = list.get(position);
        holder.week.setText(workModule.getWeek());
        holder.pdf.setText(workModule.getPdf());
        holder.upload.setText(workModule.getUri());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        TextView week, pdf, upload;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            week = itemView.findViewById(R.id.weeks);
            pdf = itemView.findViewById(R.id.Pdf);
            upload = itemView.findViewById(R.id.uploads);
        }
    }
}

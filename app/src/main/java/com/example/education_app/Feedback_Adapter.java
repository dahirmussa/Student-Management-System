package com.example.education_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Feedback_Adapter extends RecyclerView.Adapter<Feedback_Adapter.myviewholder> {

    Context c;
    ArrayList<Feedbacks> list;

    public Feedback_Adapter(Context c, ArrayList<Feedbacks> list) {
        this.c = c;
        this.list = list;
    }

    @NonNull
    @Override
    public Feedback_Adapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.itemfeedback,parent,false);
        return new Feedback_Adapter.myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Feedback_Adapter.myviewholder holder, int position) {

        Feedbacks feedbacks = list.get(position);
        holder.usernames.setText(feedbacks.getUsername());
        holder.feedbacks.setText(feedbacks.getFeedback());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        TextView usernames , feedbacks;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            usernames = itemView.findViewById(R.id.Username);
            feedbacks = itemView.findViewById(R.id.feed);

        }
    }
}

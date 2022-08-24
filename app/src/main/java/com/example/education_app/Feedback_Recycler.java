package com.example.education_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feedback_Recycler extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    Feedback_Adapter feedback_adapter;
    ArrayList<Feedbacks> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_recycler);


        recyclerView = findViewById(R.id.rv1);
        databaseReference = FirebaseDatabase.getInstance().getReference("ExamSchedules");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();


        feedback_adapter = new Feedback_Adapter(this, list);

        recyclerView.setAdapter(feedback_adapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Feedbacks feedbacks = dataSnapshot.getValue(Feedbacks.class);
                    list.add(feedbacks);
                }

                feedback_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
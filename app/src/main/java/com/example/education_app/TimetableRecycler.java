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

public class TimetableRecycler extends AppCompatActivity {


    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    TimetableAdapter timetableAdapter;
    ArrayList<Timetables> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_recycler);

        recyclerView = findViewById(R.id.rv2);
        databaseReference = FirebaseDatabase.getInstance().getReference("Timetable");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<>();


        timetableAdapter = new TimetableAdapter(this, list);

        recyclerView.setAdapter(timetableAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Timetables timetables = dataSnapshot.getValue(Timetables.class);
                    list.add(timetables);
                }

                timetableAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
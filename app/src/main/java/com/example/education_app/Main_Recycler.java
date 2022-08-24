package com.example.education_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Main_Recycler extends AppCompatActivity {

    RecyclerView rv2;
    MainAdapter mainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler);


        rv2 = (RecyclerView) findViewById(R.id.rv2);
        rv2.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<MainModel> options = new FirebaseRecyclerOptions.Builder<MainModel>().setQuery(FirebaseDatabase.getInstance().getReference().child("users"),MainModel.class).build();

        mainAdapter = new MainAdapter(options);

        rv2.setAdapter(mainAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
}
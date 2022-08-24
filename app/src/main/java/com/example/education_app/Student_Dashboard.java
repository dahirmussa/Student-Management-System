package com.example.education_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.education_app.Activities.Home;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Student_Dashboard extends AppCompatActivity
{

    FirebaseAuth firebaseAuth;
    String user_ids;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
    }

    public void timetable(View view) {
        startActivity(new Intent(getApplicationContext(), TimetableRecycler.class));
    }

    public void examschedule(View view) {
        startActivity(new Intent(getApplicationContext(), ScheduleRecycler.class));
    }

    public void Class(View view) {
       startActivity(new Intent(getApplicationContext(), Module_Recycler.class));
    }

    public void upload(View view) {
        startActivity(new Intent(getApplicationContext(), Upload_Assigment.class));
    }

    public void feed(View view) {
        startActivity(new Intent(getApplicationContext(), Feedback.class));
    }
}
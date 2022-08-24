package com.example.education_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Professor_Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_dashboard);
    }



    public void attendances(View view) {
        startActivity(new Intent(getApplicationContext(), Attendances.class));
    }

    public void Exams(View view) {
        startActivity(new Intent(getApplicationContext(), ScheduleExams.class));
    }

    public void done(View view) {
        startActivity(new Intent(getApplicationContext(), Profile.class));
    }

    public void Assignemnt(View view) {
        startActivity(new Intent(getApplicationContext(), Assignemnt.class));
    }

    public void Timetable(View view) {
        startActivity(new Intent(getApplicationContext(),Timetable.class));
    }

    public void updatework(View view) {
        startActivity(new Intent(getApplicationContext(),Upload_work.class));
    }
}
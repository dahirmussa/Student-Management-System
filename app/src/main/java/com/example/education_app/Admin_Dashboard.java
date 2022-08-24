package com.example.education_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin_Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
    }

    public void createusers(View view) {
        startActivity(new Intent(getApplicationContext(), CreateNewUsers.class));
    }


    public void modules(View view) {
        startActivity(new Intent(getApplicationContext(), Modules.class));
    }

    public void updateusers(View view) {
        startActivity(new Intent(getApplicationContext(), Main_Recycler.class));

    }
}
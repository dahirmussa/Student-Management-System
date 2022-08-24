package com.example.education_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {


    EditText users;
    EditText Feedb;
    EditText module;
    Button creates;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        users = (EditText) findViewById(R.id.users);
        Feedb = (EditText) findViewById(R.id.Feedb);
        creates = (Button) findViewById(R.id.creates);
        module = (EditText) findViewById(R.id.module);
    }


    public void creates(View view) {

        String user = users.getText().toString();
        String feeds = Feedb.getText().toString();

        if (TextUtils.isEmpty(user)) {
            users.setError("enter username again");
            return;
        }
        else if (TextUtils.isEmpty(feeds)) {
            Feedb.setError("enter the feedback again");
            return;
        }



        String usernames = users.getText().toString();
        String feedback = Feedb.getText().toString();
        String modules = module.getText().toString();

        rootNode = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Feedback").push();

        Feedbacks feedbacks = new Feedbacks(usernames,feedback, modules);
        reference.setValue(feedbacks);
        Toast.makeText(Feedback.this, "The feedback is send", Toast.LENGTH_SHORT).show();

    }
}
package com.example.education_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExamsResults extends AppCompatActivity {

    EditText Modules_names;
    EditText grades;
    EditText Feedback;
    Button sends;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference; // = rootNode.push();
    DatabaseReference pushref = reference.push();
    String ids;
    public static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams_results);

        Modules_names = findViewById(R.id.Modules_names);
        grades = findViewById(R.id.grades);
        Feedback = findViewById(R.id.Feedback);
        sends = findViewById(R.id.sends);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void Sends(View view) {
        String modules = Modules_names.getText().toString();
        String grade = grades.getText().toString();
        String feedback = Feedback.getText().toString();


        if (TextUtils.isEmpty(modules)) {
            Modules_names.setError("Enter the modules");
            return;
        } else if (TextUtils.isEmpty(grade)) {
            grades.setError("Enter grade");
            return;
        }else if(TextUtils.isEmpty(feedback)){
            Feedback.setError("enter feedback");
            return;
        }


         rootNode = FirebaseDatabase.getInstance();
         reference = FirebaseDatabase.getInstance().getReference().child("users").push();


        name = Modules_names.getText().toString();
        String Grades = grades.getText().toString();
        String Feed = Feedback.getText().toString();


        Grades grades = new Grades(name,Grades,Feed);

        reference.setValue(grades);

        Toast.makeText(ExamsResults.this, "Grades created successfully", Toast.LENGTH_SHORT).show();
    }
}
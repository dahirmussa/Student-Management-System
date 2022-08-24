package com.example.education_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_password extends AppCompatActivity {

    EditText forgot;
    Button send;
    FirebaseAuth fAuth;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        send = (Button) findViewById(R.id.send);
        forgot = (EditText) findViewById(R.id.forgot);
        fAuth = FirebaseAuth.getInstance();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });


    }

    private void validateData()
    {
        email = forgot.getText().toString();

        if(email.isEmpty())
        {
            forgot.setError("required");
        }else{
            forgotPassword();
        }
    }

    private void forgotPassword() {
        fAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Forgot_password.this, "Check your email", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Forgot_password.this, Login.class));
                    finish();
                }else{
                    Toast.makeText(Forgot_password.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void back(View view) {
        startActivity(new Intent(Forgot_password.this, Login.class));
    }
}
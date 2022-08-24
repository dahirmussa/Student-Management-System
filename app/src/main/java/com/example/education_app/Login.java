package com.example.education_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.education_app.Activities.Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {

    EditText email;
    EditText password;
    Button aLogin;
    TextView facebookapi;
    FirebaseFirestore fStore;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    //String userId;
    String user_ids;
    DatabaseReference databaseReference;
   // DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://education-app-bb58d-default-rtdb.firebaseio.com/");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        aLogin = (Button) findViewById(R.id.aLogin);
        facebookapi = (TextView) findViewById(R.id.facebookapi);
        fStore = FirebaseFirestore.getInstance();



      //  userId = firebaseAuth.getCurrentUser().getUid();
        firebaseAuth = FirebaseAuth.getInstance();
//        user = firebaseAuth.getCurrentUser();



        aLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails = email.getText().toString();
                String passwords = password.getText().toString();


                if (TextUtils.isEmpty(emails)) {
                    email.setError("enter ur username");
                    return;
                } else if (TextUtils.isEmpty(passwords)) {
                    password.setError("enter ur password again");
                    return;
                } else if (password.length() >= 8) {
                    password.setError("password must be >= 6 char");
                    return;
                }


               // final String id = firebaseAuth.getCurrentUser().getUid();
                //databaseReference = FirebaseDatabase.getInstance().getReference("users").child(id);

             //   user_id = firebaseAuth.getCurrentUser().getUid();


                firebaseAuth.signInWithEmailAndPassword(emails,passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this, "user lgoin", Toast.LENGTH_SHORT).show();
                            user_ids = firebaseAuth.getCurrentUser().getUid();
                            databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(user_ids);
                            ValueEventListener valueEventListener = new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.child("roles").getValue(String.class).equals("student"))
                                    {
                                        startActivity(new Intent(Login.this,Home.class));
                                        finish();
                                    }
                                    else if(dataSnapshot.child("roles").getValue(String.class).equals("admin"))
                                    {
                                        startActivity(new Intent(Login.this, Home.class));
                                        finish();
                                    } else if(dataSnapshot.child("roles").getValue(String.class).equals("professor"))
                                    {
                                        startActivity(new Intent(Login.this,Home.class));
                                        finish();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            };

                            databaseReference.addListenerForSingleValueEvent(valueEventListener);

                            startActivity(new Intent(getApplicationContext(),Profile.class));
                        }else{
                            Toast.makeText(Login.this, "Error"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        facebookapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this , FacebookLoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }


    public void back(View view) {
        startActivity(new Intent(getApplicationContext(),Forgot_password.class));
    }
}
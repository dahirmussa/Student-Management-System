package com.example.education_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText email;
    EditText password;
    EditText roles;
    Button  create;
    public ImageView Profile_image;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        roles = (EditText) findViewById(R.id.roles);
        create = (Button) findViewById(R.id.create);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }


    public void signup(View view) {
        String emails = email.getText().toString();
        String passwords = password.getText().toString();
        String role = roles.getText().toString();


        if (TextUtils.isEmpty(emails)) {
            email.setError("enter ur username");
            return;
        } else if (TextUtils.isEmpty(passwords)) {
            password.setError("enter ur password again");
            return;
        }else if(TextUtils.isEmpty(role)){
            roles.setError("enter ur role again");
            return;
        } else if (password.length() >= 8) {
            password.setError("password must be >= 6 char");
            return;
        }


        // create realime database



        firebaseAuth.createUserWithEmailAndPassword(emails,passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    rootNode = FirebaseDatabase.getInstance();
                    String user_id = firebaseAuth.getCurrentUser().getUid();
                    reference = FirebaseDatabase.getInstance().getReference().child("users").child(user_id);
                    // get all the values
                    //String name = firstname.getText().toString();
                    //  String emails = email.getText().toString();
                    String Emails = email.getText().toString();
                    String Passwords = password.getText().toString();
                    String Roles = roles.getText().toString();
                    String profile ="";

                    //String status = "Online";


                    UserRegisterlass UserRegisters = new UserRegisterlass(Emails,Passwords,Roles, profile, user_id);
                    reference.setValue(UserRegisters);



                    String userprofiles = "";
                    DocumentReference documentReference=firestore.collection("Users").document(user_id);
                    Map<String , Object> userdata=new HashMap<>();
                    userdata.put("email",Emails);
                    userdata.put("password",Passwords);
                    userdata.put("userprofile",userprofiles);
                    userdata.put("role",Roles);
                    userdata.put("uid",user_id);
                    userdata.put("status","Online");

                    documentReference.set(userdata).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                          //  Toast.makeText(getApplicationContext(),"Dsend success",Toast.LENGTH_SHORT).show();

                        }
                    });



                    Toast.makeText(Register.this, "User created successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
}
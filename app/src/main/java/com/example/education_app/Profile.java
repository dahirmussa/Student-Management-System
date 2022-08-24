package com.example.education_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
//import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.education_app.Activities.Home;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {

    ImageView Profile_image;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;
    String userId;
    String user_id;
    TextView email;
    BottomNavigationView bottomNavigationView;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Profile_image = (ImageView) findViewById(R.id.Profile_image);
        email = (TextView) findViewById(R.id.email);

        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();

//        userId = firebaseAuth.getCurrentUser().getUid();
       // DocumentReference documentReference = fStore.collection("users").document(userId);
        //documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
          //  @Override
            //public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
              //  if (documentSnapshot.exists()) {
                //    email.setText(documentSnapshot.getString("email"));
                //}
            //}
        //});

        //final String id = firebaseAuth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(user_id);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    String names = dataSnapshot.child("emails").getValue().toString();
                    email.setText(names);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this, "the data is not displaying in the fields", Toast.LENGTH_SHORT).show();
            }
        };

        databaseReference.addListenerForSingleValueEvent(valueEventListener);


        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference riversRef = storageReference.child("users/"+firebaseAuth.getCurrentUser().getUid()+"/profile.jpg");
        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(Profile_image);
            }
        });
    }

    public void Account(View view) {
        startActivity(new Intent(getApplicationContext(), EditProfile.class));
    }

    public void Loginout(View view) {
        // logout
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void done(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));
    }

    public void Dashboard(View view) {


        user_id = firebaseAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(user_id);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("roles").getValue(String.class).equals("student"))
                {
                    startActivity(new Intent(getApplicationContext(), Student_Dashboard.class));
                    finish();
                }
                else if(dataSnapshot.child("roles").getValue(String.class).equals("admin"))
                {
                    startActivity(new Intent(getApplicationContext(), Admin_Dashboard.class));
                    finish();
                }
                else if(dataSnapshot.child("roles").getValue(String.class).equals("professor"))
                {
                    startActivity(new Intent(getApplicationContext(), Professor_Dashboard.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

    public void darkmode(View view) {
        startActivity(new Intent(getApplicationContext(), Mode.class));
    }
}
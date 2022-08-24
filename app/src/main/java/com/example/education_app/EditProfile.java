package com.example.education_app;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.education_app.Activities.Home;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EditProfile extends AppCompatActivity {


    public static final String TAG = "TAG";
    String ImageUriAcessToken;
    private String Roles;
    private String Password;
    private String Email;

    ImageView Profile_image;
    EditText email;
    EditText password;
    EditText role;
    Button save;

    FirebaseFirestore firestore;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    FirebaseAuth fAuth;
    StorageReference storageReference;
    FirebaseUser user;
    FirebaseFirestore fStore;
    DatabaseReference databaseReference;
    DocumentReference documentReference;
    //DatabaseReference mDatabaseRef;

    String user_ida;

    // String userId;


    //DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Profile_image = (ImageView) findViewById(R.id.Profile_image);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        role = (EditText) findViewById(R.id.role);
        save = (Button) findViewById(R.id.save);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");


        //firebaseAuth = FirebaseAuth.getInstance();

        //user = fAuth.getCurrentUser();



        user_ida = fAuth.getCurrentUser().getUid();

        //reference = FirebaseDatabase.getInstance().getReference("users");

        StorageReference ProfileRef = storageReference.child("users/" + fAuth.getCurrentUser().getUid() + "/profile.jpg");
        ProfileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(Profile_image);
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(user_ida);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String names = dataSnapshot.child("emails").getValue().toString();
                    email.setText(names);
                    String user_passwords = dataSnapshot.child("password").getValue().toString();
                    password.setText(user_passwords);
                    String user_roles = dataSnapshot.child("roles").getValue().toString();
                    role.setText(user_roles);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EditProfile.this, "the data is not displaying in the fields", Toast.LENGTH_SHORT).show();
            }
        };

        databaseReference.addListenerForSingleValueEvent(valueEventListener);

        // getting the data feom real time database

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || role.getText().toString().isEmpty()) {
                    Toast.makeText(EditProfile.this, "one or more fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                rootNode = FirebaseDatabase.getInstance();
                String user_id = fAuth.getCurrentUser().getUid();
                reference = FirebaseDatabase.getInstance().getReference().child("users").child(user_id);

                Email = email.getText().toString();
                Roles = role.getText().toString();
                Password = password.getText().toString();
                Roles = role.getText().toString();
                Map<String, Object> Updates = new HashMap<>();
                Updates.put("roles", Roles);
                Updates.put("emails", Email);
                Updates.put("password", Password);
                Updates.put("userprofile", ImageUriAcessToken);
                reference.updateChildren(Updates);
                Toast.makeText(EditProfile.this, "User info has changed", Toast.LENGTH_SHORT).show();



                String user_ids = fAuth.getCurrentUser().getUid();
                documentReference=fStore.collection("Users").document(user_ids);
                Map<String, Object> update_pic = new HashMap<>();
                update_pic.put("userprofile",ImageUriAcessToken);
                //documentReference.update(Updates);

                documentReference.update(Updates).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                     //   Toast.makeText(getApplicationContext(),"Data has been uploaded",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //Intent data = getIntent();
        //String emails = data.getStringExtra("email");
        //String passwords = data.getStringExtra("password");
        //String roles = data.getStringExtra("role");

        //email.setText(emails);
        //password.setText(passwords);
        //role.setText(roles);

        //Log.d(TAG,"onCreate:" + emails + ""+ passwords + ""+roles);

        Profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageuri = data.getData();
                //  Profile_image.setImageURI(imageuri);

                uploadImageToFirebase(imageuri);
            }

        }
    }

    private void uploadImageToFirebase(Uri imageuri) {

                    // upload image to firebase storage
                    StorageReference fileRef = storageReference.child("users/" + fAuth.getCurrentUser().getUid() + "/profile.jpg");
                    fileRef.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri)
                                {
                                    ImageUriAcessToken = uri.toString();
                                    Picasso.get().load(uri).into(Profile_image);

                                    rootNode = FirebaseDatabase.getInstance();
                                    String user_id = fAuth.getCurrentUser().getUid();
                                    reference = FirebaseDatabase.getInstance().getReference().child("users").child(user_id);

                                    /// String userprofile = ImageUriAcessToken;

                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("userprofile",ImageUriAcessToken);
                                    reference.updateChildren(updates);

                                }
                            });
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditProfile.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });


                }




    public void done(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));
    }
}// end of class


        // uploading the user profile -> next


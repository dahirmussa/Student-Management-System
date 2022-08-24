package com.example.education_app;

import static com.example.education_app.EditProfile.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Upload_Assigment extends AppCompatActivity {

    TextView Names;
    TextView aday;
    TextView atime;
    EditText upload;

    Button Assigmentcreate;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_assigment);

        upload = (EditText) findViewById(R.id.upload);
        Assigmentcreate = (Button) findViewById(R.id.Assigmentcreate);
        Names = (TextView) findViewById(R.id.Names);
        aday = (TextView) findViewById(R.id.aday);
        atime = (TextView) findViewById(R.id.atime);





        storageReference = FirebaseStorage.getInstance().getReference();

        databaseReference = FirebaseDatabase.getInstance().getReference("uploadAssigmnet");

        databaseReference1 = FirebaseDatabase.getInstance().getReference("Assignement");

        Assigmentcreate.setEnabled(false);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPDF();
            }
        });


        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                  String name = dataSnapshot.child("names").getValue(String.class);
                  Names.setText(name);

                  String dates = dataSnapshot.child("date").getValue(String.class);
                  aday.setText(dates);

                  String times = dataSnapshot.child("time").getValue(String.class);
                  atime.setText(times);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void selectPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF file select"),12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 12 && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            Assigmentcreate.setEnabled(true);

           upload.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));

            Assigmentcreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadpdfFirebase(data.getData());
                }
            });
        }
    }

    private void uploadpdfFirebase(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("file is loading ....");
        progressDialog.show();

        StorageReference reference = storageReference.child("uploadPDF" + System.currentTimeMillis()+ ".pdf");

        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri uri = uriTask.getResult();

                 uploadAssigments uploadAssigments = new uploadAssigments(upload.getText().toString(),uri.toString());
                 databaseReference.child(databaseReference.push().getKey()).setValue(uploadAssigments);

                Toast.makeText(Upload_Assigment.this, "file upload", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress=(100.0 * snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("File upload.." +(int) progress+"%");
            }
        });
    }
}
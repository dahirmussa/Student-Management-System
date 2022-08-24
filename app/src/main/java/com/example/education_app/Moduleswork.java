package com.example.education_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Moduleswork extends AppCompatActivity {

    EditText week;
    EditText pdf;
    EditText upload;
    Button creates;


    FirebaseDatabase rootNodes;
    DatabaseReference reference;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moduleswork);

        week = (EditText) findViewById(R.id.week);
        pdf = (EditText) findViewById(R.id.pdf);
        upload = (EditText) findViewById(R.id.upload);
        creates = (Button) findViewById(R.id.creates);

        storageReference = FirebaseStorage.getInstance().getReference();

        databaseReference = FirebaseDatabase.getInstance().getReference("Modulework");


        creates.setEnabled(false);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPDF();
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
            creates.setEnabled(true);

            upload.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));

            creates.setOnClickListener(new View.OnClickListener() {
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

                workModule workModule = new workModule(upload.getText().toString(),uri.toString(),week.getText().toString(),pdf.getText().toString());
                databaseReference.child(databaseReference.push().getKey()).setValue(workModule);

                Toast.makeText(Moduleswork.this, "file upload", Toast.LENGTH_SHORT).show();
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

    public void creates(View view) {
        String modules = week.getText().toString();
        String day = pdf.getText().toString();
        String times = upload.getText().toString();


        if (TextUtils.isEmpty(modules)) {
            week.setError("enter the week again");
            return;
        }
        else if (TextUtils.isEmpty(day)) {
            pdf.setError("enter the pdf again");
            return;
        } else if(TextUtils.isEmpty(times)){
            upload.setError("enter the upload again");
            return;
        }
    }
}
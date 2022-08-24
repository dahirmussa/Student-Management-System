package com.example.education_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Modules extends AppCompatActivity {

    EditText Modulesnames;
    EditText Moduleskey;
    Button Modules;


    FirebaseDatabase rootNodes;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

        Modulesnames = (EditText) findViewById(R.id.Modulesnames);
        Moduleskey = (EditText) findViewById(R.id.Moduleskey);
        Modules = (Button) findViewById(R.id.Modules);

        Modules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!modulesnames() || !moduleskey()){
                    return;
                }else{
                    CreateModules();
                }
            }
        });

    }

    private void CreateModules() {
        rootNodes = FirebaseDatabase.getInstance();
        reference = rootNodes.getReference("modules").push();

        String modulesnames = Modulesnames.getText().toString();
        String moduleskeys = Moduleskey.getText().toString();

        C_Module c_module = new C_Module(modulesnames,moduleskeys);
        reference.child(modulesnames).setValue(c_module);
        Toast.makeText(this, "Successful created new Module", Toast.LENGTH_SHORT).show();
    }

    private boolean moduleskey() {
        String aModulenames = Modulesnames.getText().toString();

        if(aModulenames.isEmpty())
        {
            Modulesnames.setError("Enter the module again");
            return false;
        }else{
            Modulesnames.setError(null);
            return true;
        }
    }

    private boolean modulesnames() {
        String moduleskeys = Moduleskey.getText().toString();
        if(moduleskeys.isEmpty() || moduleskeys.length() >= 6)
        {
            Moduleskey.setError("Enter the key again");
            return false;
        }else{
            Moduleskey.setError(null);
            return true;
        }
    }

}
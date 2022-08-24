package com.example.education_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Assignemnt extends AppCompatActivity {

    EditText Assigment;
    EditText aday;
    EditText atime;
    Button Assigmentcreate;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignemnt);

        Assigment = (EditText) findViewById(R.id.Assigment);
        aday = (EditText) findViewById(R.id.aday);
        atime = (EditText) findViewById(R.id.atime);
        Assigmentcreate = (Button) findViewById(R.id.Assigmentcreate);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        aday.setInputType(InputType.TYPE_NULL);
        aday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(Assignemnt.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        aday.setText(date);

                    }
                },year, month,day);
                dialog.show();

            }
        });

        atime.setInputType(InputType.TYPE_NULL);
        atime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(atime);
            }
        });
    }

    private void showTimeDialog(EditText a_time) {
        final Calendar calendar= Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                a_time.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new TimePickerDialog(Assignemnt.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
    }

    public void send(View view) {

        String anames = Assigment.getText().toString();
        String day = aday.getText().toString();
        String times = atime.getText().toString();


        if (TextUtils.isEmpty(anames)) {
            Assigment.setError("enter the module again");
            return;
        }
        else if (TextUtils.isEmpty(day)) {
            aday.setError("enter the days again");
            return;
        } else if(TextUtils.isEmpty(times)){
            atime.setError("enter the time again");
            return;
        }

        rootNode = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Assignement").push();
        String n = Assigment.getText().toString();
        String d = aday.getText().toString();
        String t = atime.getText().toString();


        Assignements attendances = new Assignements(n,d,t);
        reference.setValue(attendances);
        Toast.makeText(Assignemnt.this, "The Assignemnt is successfully", Toast.LENGTH_SHORT).show();

    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), Professor_Dashboard.class));

    }
}
package com.example.education_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Attendances extends AppCompatActivity {

    EditText names;
    EditText days;
    EditText a_time;
    RadioButton present;
    RadioButton absent;

    Attendance attendance;
    int i = 0;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendances);

        attendance = new Attendance();
        names = (EditText) findViewById(R.id.names);
        days = (EditText) findViewById(R.id.days);
        a_time = (EditText) findViewById(R.id.time);

        present = (RadioButton) findViewById(R.id.present);
        absent = (RadioButton) findViewById(R.id.absent);

        rootNode = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Attendances").push();

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(Attendances.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        days.setText(date);

                    }
                },year, month,day);
                dialog.show();

            }
        });
        a_time.setInputType(InputType.TYPE_NULL);
        a_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(a_time);
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

        new TimePickerDialog(Attendances.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
    }

    public void create(View view) {

        String anames = names.getText().toString();
        String day = days.getText().toString();
        String times = a_time.getText().toString();
        String presents = present.getText().toString();
        String absents = absent.getText().toString();


        if (TextUtils.isEmpty(anames)) {
            names.setError("enter the module again");
            return;
        }
        else if (TextUtils.isEmpty(day)) {
            days.setError("enter the days again");
            return;
        } else if(TextUtils.isEmpty(times)){
            a_time.setError("enter the time again");
            return;
        }


        String n = names.getText().toString();
        String t = a_time.getText().toString();
        String d = days.getText().toString();
        String p = present.getText().toString();
        String a = absent.getText().toString();

       attendance.setNames(names.getText().toString());
       attendance.setDays(days.getText().toString());
       attendance.setTime(a_time.getText().toString());
        if(present.isChecked())
        {
            attendance.setPresent(p);
            reference.child(String.valueOf(i + 1)).setValue(attendance);;
        }else if(absent.isChecked())
        {
            reference.child(String.valueOf(i + 1)).setValue(attendance);;
            attendance.setAbsent(a);
        }


        Toast.makeText(Attendances.this, "The Attendances is successfully", Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), Professor_Dashboard.class));
    }
}
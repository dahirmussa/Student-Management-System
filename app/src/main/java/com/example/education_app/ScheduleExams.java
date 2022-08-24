package com.example.education_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScheduleExams extends AppCompatActivity {

    EditText Module;
    EditText days;
    EditText time_in;
    //String weekdays = "Monday";
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_exams);

        Module = findViewById(R.id.Module);
        days = findViewById(R.id.day);

        time_in = findViewById(R.id.time);
        time_in.setInputType(InputType.TYPE_NULL);
        time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(time_in);
            }
        });
    }

    public void send(View view) {
        String modules = Module.getText().toString();
        String day = days.getText().toString();
        String times = time_in.getText().toString();


        if (TextUtils.isEmpty(modules)) {
            Module.setError("enter the module again");
            return;
        }
        else if (TextUtils.isEmpty(day)) {
            days.setError("enter the days again");
            return;
        } else if(TextUtils.isEmpty(times)){
            time_in.setError("enter the time again");
            return;
        }
        
        rootNode = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("ExamSchedules").push();
        // get all the values
        //String name = firstname.getText().toString();
        //  String emails = email.getText().toString();
        String m = Module.getText().toString();
        String t = time_in.getText().toString();
        String d = days.getText().toString();
        Schedule schedule = new Schedule(m,d, t);
        reference.setValue(schedule);
        Toast.makeText(ScheduleExams.this, "The exam scheduling is successfully", Toast.LENGTH_SHORT).show();
    }
    private void showTimeDialog(final EditText time_in) {
        final Calendar calendar= Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                time_in.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new TimePickerDialog(ScheduleExams.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), Professor_Dashboard.class));
    }
}
package com.example.education_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Timetable extends AppCompatActivity {

    EditText Module_names;
    EditText days;
    EditText room;
    EditText time_in;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

   // String[] items = {"Monday", "Tuesday", "wednesday", "thursday", "Friday"};

    //AutoCompleteTextView autoCompleteTextView;

//    ArrayAdapter<String> adapteritems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

      //  autoCompleteTextView = findViewById(R.id.days);
         days = findViewById(R.id.days);
        Module_names = findViewById(R.id.Module_names);
        room = findViewById(R.id.room);

       // adapteritems = new ArrayAdapter<String>(this, R.layout.list_item);
       // autoCompleteTextView.setAdapter(adapteritems);

       // autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         //   @Override
           // public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //   String item = parent.getItemAtPosition(position).toString();
               // Toast.makeText(Timetable.this, "item"+ item, Toast.LENGTH_SHORT).show();
           // }
        //});

        time_in = findViewById(R.id.time);
        time_in.setInputType(InputType.TYPE_NULL);
        time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(time_in);
            }
        });
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

        new TimePickerDialog(Timetable.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
    }

    public void createTimetable(View view) {
        String modules = Module_names.getText().toString();
        String day = days.getText().toString();
        String times = time_in.getText().toString();


        if (TextUtils.isEmpty(modules)) {
            Module_names.setError("enter the module again");
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
        reference = FirebaseDatabase.getInstance().getReference().child("Timetable").push();
        // get all the values
        //String name = firstname.getText().toString();
        //  String emails = email.getText().toString();
        String m = Module_names.getText().toString();
        String t = time_in.getText().toString();
        String d = days.getText().toString();
        String r = room.getText().toString();
        Timetables timetables = new Timetables(m,d, t, r);
        reference.setValue(timetables);
        Toast.makeText(Timetable.this, "The Timetable scheduling is successfully", Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), Professor_Dashboard.class));
    }
}
package com.example.kinjo.myapplication;

import android.app.ActionBar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.DateFormat;
import java.util.Date;

public class Attendance extends AppCompatActivity {

    MaterialBetterSpinner spinner_class, spinner_subjet, spinner_hour, spinner_department;
    TextView textView_date;
    Button button_attendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);




        textView_date = findViewById(R.id.attendance_date);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

// textView is the TextView view that should display it
        textView_date.setText(currentDateTimeString);

        button_attendance = findViewById(R.id.attendance_button);
        spinner_class = (MaterialBetterSpinner) findViewById(R.id.spinner_class);
        spinner_subjet = (MaterialBetterSpinner) findViewById(R.id.spinner_subject);
        spinner_department = (MaterialBetterSpinner) findViewById(R.id.spinner_department);
        spinner_hour = (MaterialBetterSpinner) findViewById(R.id.spinner_hour);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.class_array, android.R.layout.simple_dropdown_item_1line);
        spinner_class.setAdapter(adapter1);

        spinner_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String spinner_value = adapterView.getItemAtPosition(position).toString();

                if (spinner_value.equals("MCA-S1")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(Attendance.this, R.array.class_mca_s1_subject, android.R.layout.simple_dropdown_item_1line);
                    spinner_subjet.setAdapter(adapter2);
                } else if (spinner_value.equals("MCA-S3")) {
                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(Attendance.this, R.array.class_mca_s3_subject, android.R.layout.simple_dropdown_item_1line);
                    spinner_subjet.setAdapter(adapter2);

                } else {
                    ArrayAdapter adapter3 = ArrayAdapter.createFromResource(Attendance.this, R.array.class_mca_s5_subject, android.R.layout.simple_dropdown_item_1line);
                    spinner_subjet.setAdapter(adapter3);

                }

            }
        });

        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.attendance_hour, android.R.layout.simple_dropdown_item_1line);
        spinner_hour.setAdapter(adapter2);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.department_array, android.R.layout.simple_dropdown_item_1line);
        spinner_department.setAdapter(adapter3);


        button_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(Attendance.this,Attendance_popup.class);
                startActivity(intent);

            }});


        }

}






package com.example.kinjo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class AttendanceReport extends AppCompatActivity {

    MaterialBetterSpinner spinner_class_stud_list,spinner_class,spinner_department;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_report);

        spinner_class_stud_list=findViewById(R.id.spinner_report_select_studnet);
        spinner_class = (MaterialBetterSpinner) findViewById(R.id.spinner_report_class);
        spinner_department = (MaterialBetterSpinner) findViewById(R.id.spinner_report_department);

        String[] student=new String[1];
        student[0]="student name";

        ArrayAdapter<String> arrayAdapter_class =
                new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,
                        student);
        spinner_class_stud_list =(MaterialBetterSpinner)findViewById(R.id.spinner_report_select_studnet);
        spinner_class_stud_list.setAdapter(arrayAdapter_class);

        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.department_array, android.R.layout.simple_dropdown_item_1line);
        spinner_department.setAdapter(adapter3);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.class_array, android.R.layout.simple_dropdown_item_1line);
        spinner_class.setAdapter(adapter1);


    }
}

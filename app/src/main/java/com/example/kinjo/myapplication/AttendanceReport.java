package com.example.kinjo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class AttendanceReport extends AppCompatActivity {

    MaterialBetterSpinner spinner_class_stud_list,spinner_class,spinner_department;
    String student_name;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_report);

        spinner_class_stud_list=findViewById(R.id.spinner_report_select_studnet);
        spinner_class = (MaterialBetterSpinner) findViewById(R.id.spinner_report_class);
        spinner_department = (MaterialBetterSpinner) findViewById(R.id.spinner_report_department);

        String[] student={"Liam",
                "Noah", "William", "James", "Logan", "Benjamin", "Mason", "Elijah", "Olive", "Jacob", "Lucas", "Michael",
                "Alexander", "Ethan",
                "Daniel", "Matthew",
                "Aiden", "Henry",
                "Joseph", "Jackson",
                "Samuel", "Sebastian", "David"};


        ArrayAdapter<String> arrayAdapter_class =
                new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,
                        student);
        spinner_class_stud_list =(MaterialBetterSpinner)findViewById(R.id.spinner_report_select_studnet);
        spinner_class_stud_list.setAdapter(arrayAdapter_class);

        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.department_array, android.R.layout.simple_dropdown_item_1line);
        spinner_department.setAdapter(adapter3);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.class_array, android.R.layout.simple_dropdown_item_1line);
        spinner_class.setAdapter(adapter1);



        spinner_class_stud_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                student_name= parent.getItemAtPosition(position).toString();
            }
        });

        button=findViewById(R.id.attendance_report_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AttendanceReport.this,AttendanceView.class);
                startActivity(intent);
            }
        });


    }
}

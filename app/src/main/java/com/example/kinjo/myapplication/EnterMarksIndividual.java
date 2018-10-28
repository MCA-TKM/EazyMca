package com.example.kinjo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class EnterMarksIndividual extends AppCompatActivity {
    MaterialBetterSpinner spinner_select_student;

    String[] List_students = {"List of Students" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_marks_individual);

      ArrayAdapter<String> arrayAdapter_student =
                new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, List_students);

        spinner_select_student = (MaterialBetterSpinner)findViewById(R.id.student_list_spinner);

        spinner_select_student.setAdapter(arrayAdapter_student);


    }
}

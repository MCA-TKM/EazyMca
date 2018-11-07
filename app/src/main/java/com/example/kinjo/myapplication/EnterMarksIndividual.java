package com.example.kinjo.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class EnterMarksIndividual extends AppCompatActivity {
    MaterialBetterSpinner spinner_select_student;
    Button button_max_mark;
    TextView textView_max_marks;

    String[] List_students = {"List of Students" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_marks_individual);

        button_max_mark=findViewById(R.id.set_max_mark_button);
        textView_max_marks=findViewById(R.id.max_marks_edittext);
        Intent intent = getIntent();

        String type=intent.getStringExtra("mark_type");


        if (type.equals("Test"))
        {
           // button_max_mark.setClickable(false);
            textView_max_marks.setFocusable(false);
            textView_max_marks.setEnabled(false);
          //  textView_max_marks.setCursorVisible(false);
         //   textView_max_marks.setKeyListener(null);
          // textView_max_marks.setBackgroundColor(Color.TRANSPARENT);
            Toast.makeText(this, intent.getStringExtra("mark_type"), Toast.LENGTH_SHORT).show();

        }

      ArrayAdapter<String> arrayAdapter_student =
                new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, List_students);

        spinner_select_student = (MaterialBetterSpinner)findViewById(R.id.student_list_spinner);

        spinner_select_student.setAdapter(arrayAdapter_student);


    }
}

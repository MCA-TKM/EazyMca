package com.example.kinjo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class EnterMarks extends AppCompatActivity {

    MaterialBetterSpinner spinner_class_sub_list,spinner_mark_number;
    String[] List_class_sub = {"testdata1", "testdata2", "testdata3", "testdata4", "5", "testdata6"};
    String[] List_No_of_mark = {"1", "2", "3", "4", "5"};

    Button button_enter_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_marks);



        ArrayAdapter<String> arrayAdapter_class =
                new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,
                        List_class_sub);
        spinner_class_sub_list =
                (MaterialBetterSpinner)findViewById(R.id.spinner_class_sub);
        spinner_class_sub_list.setAdapter(arrayAdapter_class);

        ArrayAdapter<String > arrayAdapter_number= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,
                List_No_of_mark);
        spinner_mark_number=(MaterialBetterSpinner)findViewById(R.id.spinner_mark_no);

        spinner_mark_number.setAdapter(arrayAdapter_number);


       button_enter_mark=findViewById(R.id.enter_mark_button);

        button_enter_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EnterMarks.this,EnterMarksIndividual.class);
                startActivity(intent);
            }
        });


    }
}

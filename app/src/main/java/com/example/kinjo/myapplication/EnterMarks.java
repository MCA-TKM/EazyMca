package com.example.kinjo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class EnterMarks extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    MaterialBetterSpinner spinner_class,spinner_subjet;


    MaterialBetterSpinner spinner_class_sub_list,spinner_mark_number;
    String[] List_class_sub = {"testdata1", "testdata2", "testdata3", "testdata4", "5", "testdata6"};
    String[] List_No_of_mark = {"1", "2", "3", "4", "5"};

    Button button_enter_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_marks);

        radioGroup = (RadioGroup) findViewById(R.id.radio_mark_type);


        ArrayAdapter<String > arrayAdapter_number= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,
                List_No_of_mark);
        spinner_mark_number=(MaterialBetterSpinner)findViewById(R.id.spinner_mark_no);

        spinner_mark_number.setAdapter(arrayAdapter_number);



        spinner_class = (MaterialBetterSpinner) findViewById(R.id.spinner_classm);
        spinner_subjet = (MaterialBetterSpinner) findViewById(R.id.spinner_subjectm);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.class_array, android.R.layout.simple_dropdown_item_1line);
        spinner_class.setAdapter(adapter1);
        ArrayAdapter adapter4 = ArrayAdapter.createFromResource(this, R.array.class_mca_s5_subject, android.R.layout.simple_dropdown_item_1line);
        spinner_subjet.setAdapter(adapter4);




        spinner_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
              String  spinner_value = adapterView.getItemAtPosition(position).toString();


                if (spinner_value.equals("MCA-S1")) {



                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(EnterMarks.this, R.array.class_mca_s1_subject, android.R.layout.simple_dropdown_item_1line);
                    spinner_subjet.setAdapter(adapter2);
                } else if (spinner_value.equals("MCA-S3")) {



                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(EnterMarks.this, R.array.class_mca_s3_subject, android.R.layout.simple_dropdown_item_1line);
                    spinner_subjet.setAdapter(adapter2);

                } else {



                    ArrayAdapter adapter3 = ArrayAdapter.createFromResource(EnterMarks.this, R.array.class_mca_s5_subject, android.R.layout.simple_dropdown_item_1line);
                    spinner_subjet.setAdapter(adapter3);

                }

            }
        });



       button_enter_mark=findViewById(R.id.enter_mark_button);

        button_enter_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String type=radioButton.getText().toString();
                Intent intent=new Intent(EnterMarks.this,EnterMarksIndividual.class);

                intent.putExtra("mark_type",type);
                startActivity(intent);
                finish();
            }
        });


    }
}

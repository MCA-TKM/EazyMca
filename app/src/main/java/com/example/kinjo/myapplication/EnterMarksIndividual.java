package com.example.kinjo.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnterMarksIndividual extends AppCompatActivity {
    MaterialBetterSpinner spinner_select_student;
    Button button_max_mark;
    TextView textView_max_marks;
    ProgressDialog pd;

    String[] List_students = {"List of Students" };
    String URL_POST="https://bibinbaby1996.000webhostapp.com/select.php";
    int i=301;

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

        spinner_select_student  .setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String  spinner_value = adapterView.getItemAtPosition(position).toString();


                TextView textView=findViewById(R.id.namestudent_textview);
                TextView textView1=findViewById(R.id.rollstudent_textview);
                i++;
                textView1.setText("Roll No:  "+i);
                textView.setText("Name:  "+spinner_value);


            }
        });


        Button button=findViewById(R.id.set_max_mark_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText editText=findViewById(R.id.max_marks_edittext);
                TextView textView=findViewById(R.id.max_marks);
                textView.setText("/"+editText.getText().toString());

            }
        });

        Button button1=findViewById(R.id.apply_to_alll_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText=findViewById(R.id.mark_apply_to_all);
                Toast.makeText(EnterMarksIndividual.this, editText.getText().toString()+"   Marks Applied to all students", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        Button button2=findViewById(R.id.finish_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(EnterMarksIndividual.this, "Marks Uploaded Successfully ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        ServerConnetion(URL_POST,"S1-MCA");


    }


    private void ServerConnetion (String URL_POST, final String class_name) {

        pd= new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Loading\nPlease wait...");
        pd.show();

        HttpsTrustManager.allowAllSSL();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {




                try {


                    List<String> allNames = new ArrayList<String>();

                    JSONArray cast = new JSONArray(response);
                    for (int i=0; i<cast.length(); i++) {
                        JSONObject actor = cast.getJSONObject(i);
                        String name = actor.getString("name");
                        allNames.add(name);
                    }
                    pd.dismiss();





                    ArrayAdapter<String> arrayAdapter_class =
                            new ArrayAdapter<String>(EnterMarksIndividual.this,android.R.layout.simple_dropdown_item_1line, allNames);
                    spinner_select_student.setAdapter(arrayAdapter_class);



                }catch (Exception e){

                    pd.dismiss();


                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pd.dismiss();


            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                //  params.put("QUESTION",question);
                params.put("class",class_name );

                return params;

            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




}

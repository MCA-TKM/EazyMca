package com.example.kinjo.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkReport extends AppCompatActivity {

    MaterialBetterSpinner spinner_class_stud_list,spinner_class,spinner_department;

    String spinner_value,name;
    ProgressDialog pd;


    String URL_POST="https://bibinbaby1996.000webhostapp.com/select.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_report);


        spinner_class = (MaterialBetterSpinner) findViewById(R.id.spinner_mark_report_class);
        spinner_department = (MaterialBetterSpinner) findViewById(R.id.spinner_mark_report_department);


        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.department_array, android.R.layout.simple_dropdown_item_1line);
        spinner_department.setAdapter(adapter3);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.class_array, android.R.layout.simple_dropdown_item_1line);
        spinner_class.setAdapter(adapter1);


        spinner_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                spinner_value = adapterView.getItemAtPosition(position).toString();


                if (spinner_value.equals("MCA-S1")) {


                    ServerConnetion(URL_POST,"S1-MCA");



                } else if (spinner_value.equals("MCA-S3")) {





                } else {



                }

            }
        });





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

                    spinner_class_stud_list=findViewById(R.id.spinner_mark_report_select_studnet);






                    String[] student=new String[1];
                    student[0]="student name";

                    ArrayAdapter<String> arrayAdapter_class =
                            new ArrayAdapter<String>(MarkReport.this,android.R.layout.simple_dropdown_item_1line, allNames);
                    spinner_class_stud_list.setAdapter(arrayAdapter_class);



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

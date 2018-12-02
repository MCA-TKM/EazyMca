package com.example.kinjo.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    EditText editTextUsername,editTextPassword;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton=findViewById(R.id.button_login);
        editTextUsername=findViewById(R.id.edit_text_username);
        editTextPassword=findViewById(R.id.edit_text_password);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String usernamre=editTextUsername.getText().toString();
                String password=editTextPassword.getText().toString();

                if(usernamre.equals("stud")&&password.equals("pass"))
                {

                Intent intent=new Intent(MainActivity.this,StudentHome.class);
                startActivity(intent);


                }else{



                String URL_POST="https://bibinbaby1996.000webhostapp.com/new.php";

                Boolean connected=false;

                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                }
                if (connected) {
                    ServerConnetion(URL_POST, usernamre, password);

                }
                else {
                    Toast.makeText(MainActivity.this, "Check Internet Conenction", Toast.LENGTH_SHORT).show();
                }}

            }
        });


    }
    private void ServerConnetion (String URL_POST, final String username, final String password) {

        pd= new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Loading\nPlease wait...");
        pd.show();

        HttpsTrustManager.allowAllSSL();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{

                    JSONObject jsonObject=new JSONObject(response);
                    String status=jsonObject.getString("status");

                    if (status.equals("true"))
                    {
                        String name=jsonObject.getString("name");
                        String department=jsonObject.getString("department");
                        String image=jsonObject.getString("image");
                        String id=jsonObject.getString("teacherID");


                        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                        intent.putExtra("name",name);
                        intent.putExtra("image",image);
                        intent.putExtra("department",department);
                        intent.putExtra("ID",id);
                        startActivity(intent);
                        pd.dismiss();

                    }
                    else {
                        Toast.makeText(MainActivity.this, "Enter valid Username/Password", Toast.LENGTH_LONG).show();
                        pd.dismiss();
                    }

                }catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, "Erorr while loging in"+e.toString(), Toast.LENGTH_LONG).show();
                    pd.dismiss();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Enter valid Username/Password", Toast.LENGTH_LONG).show();
                pd.dismiss();

            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


                //  params.put("QUESTION",question);
                params.put("username",username );
                params.put("password", password);

                return params;

            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

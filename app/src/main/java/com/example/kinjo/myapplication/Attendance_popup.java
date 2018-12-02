package com.example.kinjo.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.transition.TransitionManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
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

public class   Attendance_popup extends AppCompatActivity {

    TextView textView_roll_no,textView_absent;
    int roll_no;
    Button button_cancel;
    ProgressDialog pd;

    String cls,hr,sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_popup);


        Intent intent=getIntent();
        final int number=24;

        cls=intent.getStringExtra("class");
        hr=intent.getStringExtra("hour");
        sub=intent.getStringExtra("subject");

        Toast.makeText(this, sub, Toast.LENGTH_SHORT).show();

        button_cancel=findViewById(R.id.cancel_button);

        final Animation animation_up,animation_left,animation_right;
        animation_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up);
        animation_left = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_left);
        animation_right = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_right);



        roll_no=1;
        textView_absent=findViewById(R.id.absent_textview);
        textView_roll_no=findViewById(R.id.roll_no);
        textView_roll_no.setText(""+roll_no);


        textView_roll_no.setOnTouchListener(new OnSwipeTouchListener(Attendance_popup.this) {


            public void onSwipeTop() {


                textView_roll_no.startAnimation(animation_up);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        String s=textView_absent.getText().toString();
                        String n=String.valueOf(roll_no);
                        textView_absent.setText(s+n+",");
                        roll_no=roll_no+1;
                        if (roll_no>number)
                        {
                            att_alert(textView_absent.getText().toString());
                        }else
                        textView_roll_no.setText(""+roll_no);

                    }
                }, 200);


            }
            public void onSwipeRight() {


                if (roll_no!=1) {
                    textView_roll_no.startAnimation(animation_right);

                    String s=textView_absent.getText().toString();
                    roll_no = roll_no - 1;
                    String n=String.valueOf(roll_no)+",";
                    s=s.replaceAll(n,"");
                    textView_absent.setText(s);
                    textView_roll_no.setText(""+roll_no);
                }
            }
            public void onSwipeLeft() {


                textView_roll_no.startAnimation(animation_left);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        roll_no=roll_no+1;
                        if (roll_no>number)
                        {
                            att_alert(textView_absent.getText().toString());
                        }else
                            textView_roll_no.setText(""+roll_no);

                    }
                }, 200);


            }
            public void onSwipeBottom() {


            }

        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void att_alert(final String string)
    {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(string+"\nAre you sure want to Submit");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String URL_POST="https://bibinbaby1996.000webhostapp.com/absent.php";

                        String s=string.replaceAll("Absenties:","");

                       // Toast.makeText(Attendance_popup.this, s, Toast.LENGTH_SHORT).show();

                        ServerConnetion(URL_POST,s,cls,hr,sub);

                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void ServerConnetion (String URL_POST, final String absenties, final String a , final String b, final String c) {

        pd= new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Loading\nPlease wait...");
        pd.show();

        HttpsTrustManager.allowAllSSL();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Toast.makeText(Attendance_popup.this, "Attendance Submitted Successfully", Toast.LENGTH_LONG).show();
                finish();
                pd.dismiss();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(Attendance_popup.this, error.toString(), Toast.LENGTH_SHORT).show();

                pd.dismiss();

            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


                //  params.put("QUESTION",question);
                params.put("absenties",absenties );
                params.put("class",a);
                params.put("hour",b);
                params.put("subject",c);

                return params;

            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}

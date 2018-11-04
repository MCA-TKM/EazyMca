package com.example.kinjo.myapplication;

import android.os.Handler;
import android.support.transition.TransitionManager;
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

public class   Attendance_popup extends AppCompatActivity {

    TextView textView_roll_no,textView_absent;
    int roll_no;
    Button button_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_popup);

        button_cancel=findViewById(R.id.cancel_button);

        final Animation animation_up,animation_left,animation_right;
        animation_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up);
        animation_left = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_left);
        animation_right = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_right);


        int no_of_students=44;
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
                        textView_roll_no.setText(""+roll_no);

                    }
                }, 400);


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
                        textView_roll_no.setText(""+roll_no);

                    }
                }, 400);


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
}

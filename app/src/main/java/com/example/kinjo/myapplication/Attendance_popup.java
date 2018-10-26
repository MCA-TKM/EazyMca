package com.example.kinjo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Attendance_popup extends AppCompatActivity {

    TextView textView_roll_no;
    int roll_no;

    Button button_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_popup);

        button_cancel=findViewById(R.id.cancel_button);

        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width=displayMetrics.widthPixels;
        int height =displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.6));

        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity=0;
        params.x=0;
        params.y=-20;

        getWindow().setAttributes(params);

        roll_no=1;
        textView_roll_no=findViewById(R.id.roll_no);
        textView_roll_no.setText(""+roll_no);


        textView_roll_no.setOnTouchListener(new OnSwipeTouchListener(Attendance_popup.this) {
            public void onSwipeTop() {

                roll_no=roll_no+1;
                textView_roll_no.setText(""+roll_no);

            }
            public void onSwipeRight() {

                if (roll_no!=1) {
                    roll_no = roll_no - 1;
                    textView_roll_no.setText(""+roll_no);
                }
            }
            public void onSwipeLeft() {

                roll_no=roll_no+1;
                textView_roll_no.setText(""+roll_no);

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

package com.xk.chapter1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(android.R.style.Theme_Translucent_NoTitleBar);
        setContentView(R.layout.activity_2);
        Log.d("Activity2", "onCreate-->");
        TextView textView = new TextView(this);

        textView.setText("222");

        ((ViewGroup) findViewById(android.R.id.content)).addView(textView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Activity2", "onResume-->");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Activity2", "onStart-->");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Activity2", "onStop-->");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Activity2", "onPause-->耗时操作开始");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("Activity2", "onPause耗时操作结束-->");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Activity2", "onRestart-->");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Activity2", "onDestroy-->");
    }
}

package com.xk.chapter1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Log.d("Activity1", "onCreate-->");
        Button textView = new Button(this);

        textView.setText("111");

        ((ViewGroup) findViewById(android.R.id.content)).addView(textView);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity1.this, Activity2.class);
//                intent.setAction("com.pas.intenttest.sec");
//                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                intent.addCategory("android.intent.category.DEFAULT");
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

//        Calling startActivity() from outside of an Activity context requires the FLAG_ACTIVITY_NEW_TASK flag. Is this really what you want?
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Activity1", "onResume-->");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Activity1", "onStart-->");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Activity1", "onStop-->");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Activity1", "onPause耗时操作开始-->");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("Activity1", "onPause耗时操作结束-->");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Activity1", "onRestart-->");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Activity1", "onDestroy-->");
    }
}

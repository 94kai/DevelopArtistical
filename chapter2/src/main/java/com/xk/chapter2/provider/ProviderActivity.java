package com.xk.chapter2.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xk.chapter2.R;

/**
 * Created by xuekai on 2017/9/17.
 */

public class ProviderActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:

                Uri uri = Uri.parse("content://com.xk.chapter2.provider.BookProvider");
                Log.d("ProviderActivity", "onClick-->" + Thread.currentThread());
                getContentResolver().query(uri, null, null, null, null);

                break;
            case R.id.btn2:
                Uri uri1 = Uri.parse("content://com.xk.chapter2.provider.BookProvider");
                Log.d("ProviderActivity", "onClick-->" + Thread.currentThread());
                getContentResolver().insert(uri1, new ContentValues());

                break;
            case R.id.btn3:
                Uri uri2 = Uri.parse("content://com.xk.chapter2.provider.BookProvider");
                Log.d("ProviderActivity", "onClick-->" + Thread.currentThread());
                getContentResolver().update(uri2, new ContentValues(), null, null);

                break;
            case R.id.btn4:
                Uri uri3 = Uri.parse("content://com.xk.chapter2.provider.BookProvider");
                Log.d("ProviderActivity", "onClick-->" + Thread.currentThread());
                getContentResolver().delete(uri3, "", null);
                Bundle add = getContentResolver().call(uri3, "sub", "1a2", new Bundle());
                Toast.makeText(this, add.getInt("result")+"", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}

package com.xk.chapter2.tcpdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xk.chapter2.R;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by xuekai on 2017/9/18.
 */

public class TcpActivity extends Activity {

    private Socket localhost;
    private OutputStream outputStream;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tcp);
    }

    /**
     * 连接服务
     *
     * @param v
     */
    public void conn(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                startService(new Intent(TcpActivity.this, TcpService.class));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    localhost = new Socket("localhost", 8216);
                    outputStream = localhost.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    bufferedWriter.write("你好啊\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 连接服务
     *
     * @param v
     */
    public void disconn(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    localhost.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 连接服务
     *
     * @param v
     */
    public void send(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    bufferedWriter.write("你好啊" + System.currentTimeMillis()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

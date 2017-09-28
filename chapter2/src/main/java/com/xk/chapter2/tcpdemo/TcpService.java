package com.xk.chapter2.tcpdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xuekai on 2017/9/18.
 */

public class TcpService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new TcpServerThread()).start();
    }


    class TcpServerThread implements Runnable {

        @Override
        public void run() {
            try {
                //创建一个本地socketserver
                ServerSocket serverSocket = new ServerSocket(8216);
                boolean flag = true;//在这里控制server的开启
                while (flag) {
                    final Socket accept = serverSocket.accept();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            handlerClient(accept);
                        }
                    }).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handlerClient(Socket accept) {
        try {
            InputStream inputStream = accept.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            boolean flag = true;
            while (flag) {
                String s = bufferedReader.readLine();
                if (s != null) {
                    Log.d("TcpService", "handlerClient-->" + s);
                } else {
                    flag = false;
                    Log.d("TcpService", "handlerClient-->请求断开");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

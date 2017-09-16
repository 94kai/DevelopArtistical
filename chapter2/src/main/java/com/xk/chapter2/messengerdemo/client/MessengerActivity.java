package com.xk.chapter2.messengerdemo.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.xk.chapter2.R;
import com.xk.chapter2.messengerdemo.server.MessengerService;

/**
 * Created by xuekai on 2017/9/14.
 */

public class MessengerActivity extends Activity {

    private Messenger messenger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        bindService(new Intent(this, MessengerService.class), conn, BIND_AUTO_CREATE);


    }

    public void addBook(View v) throws RemoteException {
        Message obtain = Message.obtain();
        obtain.what=0;
//        obtain.arg1=1;
        Bundle bundle = new Bundle();
        bundle.putCharSequence("book","book");
        obtain.setData(bundle);
        obtain.replyTo=new Messenger(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                CharSequence reply = msg.getData().getCharSequence("reply");
                Log.d("MessengerActivity","handleMessage-->"+reply);
            }
        });
        messenger.send(obtain);
    }


    ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}

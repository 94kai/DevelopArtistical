package com.xk.chapter2.messengerdemo.server;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by xuekai on 2017/9/14.
 */

public class MessengerService extends Service {


    private static class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0) {
                Log.d("MessageHandler","handleMessage-->添加图书（client->server）"+msg.getData().getCharSequence("book"));
//                        +msg.arg1);

                Messenger replyTo = msg.replyTo;
                msg.getData().putCharSequence("reply","收到");
                try {
                    replyTo.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    Messenger messenger = new Messenger(new MessageHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}

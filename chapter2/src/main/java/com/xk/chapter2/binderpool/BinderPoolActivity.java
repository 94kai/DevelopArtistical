package com.xk.chapter2.binderpool;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;

import com.xk.chapter2.R;

/**
 * Created by xuekai on 2017/9/19.
 */

public class BinderPoolActivity extends Activity {
    private IBinderPool iBinderPool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);

        bindService(new Intent(this, BinderPoolService.class), conn, BIND_AUTO_CREATE);
    }


    ServiceConnection conn = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinderPool = IBinderPool.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void fun1(View v) {
        try {
            IBinder iBinder = iBinderPool.queryBinder(1);
            IFunction1.Stub.asInterface(iBinder).function1(1, 1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void fun2(View v) {
        try {
            IBinder iBinder = iBinderPool.queryBinder(2);
            IFunction2.Stub.asInterface(iBinder).function2(2, 2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

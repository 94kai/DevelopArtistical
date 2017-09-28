package com.xk.chapter2.binderpool;

import android.os.RemoteException;
import android.util.Log;

/**
 * IFunction1的实现类
 * Created by xuekai on 2017/9/19.
 */

public class Function1Impl extends IFunction1.Stub {
    @Override
    public void function1(int a, int b) throws RemoteException {
        Log.d("Function1Impl", "function1-->" + a + " " + b);
    }
}

package com.xk.chapter2.binderpool;

import android.os.RemoteException;
import android.util.Log;

/**
 * Created by xuekai on 2017/9/19.
 */

public class Function2Impl extends IFunction2.Stub {
    @Override
    public void function2(int a, int b) throws RemoteException {
        Log.d("Function2Impl", "function2-->" + a + " " + b);
    }
}

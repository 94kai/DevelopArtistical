package com.xk.chapter2.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by xuekai on 2017/9/19.
 */

public class BinderPoolService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new BinderPool();
    }


    class BinderPool extends IBinderPool.Stub {
        @Override
        public IBinder queryBinder(int binderCode) {
            switch (binderCode) {
                case 1:
                    return new Function1Impl();
                case 2:
                    return new Function2Impl();
            }
            return null;
        }
    }

}

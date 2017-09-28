package com.xk.chapter2.binderpool;

import android.os.IBinder;

/**
 * Created by xuekai on 2017/9/19.
 */

public interface IBinderPool2 {
    IBinder queryBinder(int binderCode);
}

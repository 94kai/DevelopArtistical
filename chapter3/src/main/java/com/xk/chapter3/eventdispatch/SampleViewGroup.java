package com.xk.chapter3.eventdispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by xuekai on 2017/9/24.
 */

public class SampleViewGroup extends FrameLayout {
    public SampleViewGroup(Context context) {
        super(context);
    }

    public SampleViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("SampleViewGroup","onTouch-->");
                return false;
            }
        });
    }

    public SampleViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {//事件分发到哪个view，哪个view的这个方法就会被调用，返回结果受到自己的ontouchevent（是否消费）、onInterceptTouchEvent（是否拦截）影响
        Log.d("SampleViewGroup","dispatchTouchEvent-->");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//在dispatchTouchEvent中调用，表示是否处理事件，如果不消费，那么同一个事件序列后续都不会再走这个方法，如果消费，后续事件都会交给他
        Log.d("SampleViewGroup","onTouchEvent-->");
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {//被dispatchTouchEvent调用，一旦拦截，同一个事件序列的其他事件就不会再走这个方法了（拦截之后，后续的事件都由这个View处理，不需要在验证是否拦截了）
        Log.d("SampleViewGroup","onInterceptTouchEvent-->");
        return true;
    }
}

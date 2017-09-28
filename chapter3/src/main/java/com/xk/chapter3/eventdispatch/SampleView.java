package com.xk.chapter3.eventdispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xuekai on 2017/9/24.
 */

public class SampleView extends View{
    public SampleView(Context context) {
        super(context);
    }

    public SampleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("SampleView","onTouch-->");
                return false;
            }
        });
    }

    public SampleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("SampleView","dispatchTouchEvent-->");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("SampleView","onTouchEvent-->");
        return true;
    }


}

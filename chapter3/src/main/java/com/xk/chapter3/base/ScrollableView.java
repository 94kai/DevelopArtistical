package com.xk.chapter3.base;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by xuekai on 2017/9/21.
 */

public class ScrollableView extends FrameLayout {

    private Scroller scroller;

    public ScrollableView(Context context) {
        super(context);
        setBackgroundColor(0xff00ff00);
        Button button = new Button(context);
        button.setLayoutParams(new LayoutParams(100, 100));
        addView(button);
        scroller = new Scroller(context);
    }

    public ScrollableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    boolean isUsepost;

    public void smoothScrollTo(boolean isUsePost) {
        this.isUsepost = isUsePost;
        scroller.startScroll(10, 10, -500, -200, 1000);
        invalidate();
//        scrollTo(10, 10);
        Log.d("ScrollableView", "smoothScrollTo-->2");
    }


    @Override
    public void computeScroll() {
        Log.d("ScrollableView", "computeScroll-->");
        if (scroller.computeScrollOffset()) {
            Log.d("ScrollableView", "computeScroll-->" + scroller.getCurrX() + scroller.getCurrY());
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("ScrollableView", "onDraw-->");
        super.onDraw(canvas);
    }
}


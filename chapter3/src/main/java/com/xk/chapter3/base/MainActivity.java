package com.xk.chapter3.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xk.chapter3.R;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;
    private ViewGroup root;
    private Button slide;
    private ScrollableView scrollableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetector = new GestureDetector(this, this);
        gestureDetector.setIsLongpressEnabled(false);//是否开启长按，如果开了，可以捕获到长按事件，但是长按后不可以捕获到滑动事件

        final TextView ltrb = (TextView) findViewById(R.id.ltrb);
        final TextView translation = (TextView) findViewById(R.id.translation);
        final TextView xy = (TextView) findViewById(R.id.xy);


        Button left1 = (Button) findViewById(R.id.left1);
        Button left2 = (Button) findViewById(R.id.left2);
        Button left3 = (Button) findViewById(R.id.left3);
        Button right1 = (Button) findViewById(R.id.right1);
        Button right2 = (Button) findViewById(R.id.right2);
        Button right3 = (Button) findViewById(R.id.right3);
        Button print1 = (Button) findViewById(R.id.print1);
        Button print2 = (Button) findViewById(R.id.print2);
        Button print3 = (Button) findViewById(R.id.print3);
        root = (ViewGroup) findViewById(R.id.root);
        slide = (Button) findViewById(R.id.slide);

        left1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ltrb.setLeft(ltrb.getLeft() - 5);
            }
        });
        left2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translation.setTranslationX(translation.getTranslationX() - 5);
            }
        });
        left3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xy.setX(xy.getX() - 5);

            }
        });
        right1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ltrb.setLeft(ltrb.getLeft() + 5);
            }
        });
        right2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translation.setTranslationX(translation.getTranslationX() + 5);

            }
        });
        right3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xy.setX(xy.getX() + 5);

            }
        });
        print1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("EventDispatchActivity", "onClick-->111:left" + ltrb.getLeft() + " translationX" + ltrb.getTranslationX() + " x:" + ltrb.getX());
            }
        });
        print2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("EventDispatchActivity", "onClick-->222:left" + translation.getLeft() + " translationX" + translation.getTranslationX() + " x:" + translation.getX());

            }
        });
        print3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("EventDispatchActivity", "onClick-->333:left" + xy.getLeft() + " translationX" + xy.getTranslationX() + " x:" + xy.getX());

            }
        });


        //改变left
//        改变translationx
//        改变x
//        查看这几种的效果 以及打印他们的三个值


        scrollableView = new ScrollableView(this);
        scrollableView.setLayoutParams(new FrameLayout.LayoutParams(1000, 500));
        ((FrameLayout) findViewById(R.id.content)).addView(scrollableView);
        scrollableView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("EventDispatchActivity", "onClick-->");
//                scrollableView.scrollBy(10,10);
                scrollableView.smoothScrollTo(true);
            }
        });
        scrollableView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("EventDispatchActivity", "onLongClick-->");
//                scrollableView.scrollBy(10,10);
                scrollableView.smoothScrollTo(false);
                return true;
            }
        });


        slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideView();
            }
        });
    }


    public void slideView() {
        slideViewByScrollTo();
//        slideViewByViewAnimator();
//        slideViewByObjectAnimator();
    }

    /**
     * 通过scrollto
     * 需要拿到父View
     * 这里是突变，可以通过scroller使其渐变
     */
    public void slideViewByScrollTo() {
        root.scrollTo(0 - 100, 0 - 100);
        //滚动完成后，新位置的view还可以响应事件
    }

    /**
     * 通过补间动画
     */
    public void slideViewByViewAnimator() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 100, 0, 100);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(1000);
        scrollableView.startAnimation(translateAnimation);
        //滚动完成后，旧位置的view还可以响应事件

    }

    /**
     * 属性动画
     */
    public void slideViewByObjectAnimator() {
//        ObjectAnimator.ofFloat(scrollableView,"translationX",0,100).setDuration(1000).start();
//        ObjectAnimator.ofFloat(scrollableView,"x",0,100).setDuration(1000).start();
//        ObjectAnimator.ofInt(scrollableView,"left",0,100).setDuration(1000).start();
//        以上都是可行的，这玩意很灵活
    }

    /**
     * 通过layoutparams，这个就不写了，跟属性动画一样，很灵活的，甚至比属性动画更灵活，正如android开发艺术中的一个小实例一样，控制别的view，使得另一个view的位置改变，总之灵活运用吧
     */
    public void slideViewByLayoutparams() {
//        scrollableView.getLayoutParams();
    }


    float lastX = 0;
    float lastY = 0;

    VelocityTracker velocityTracker = VelocityTracker.obtain();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        velocityTracker.addMovement(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                double sqrt = Math.sqrt((x - lastX) * (x - lastX) + (y - lastY) * (y - lastY));

                int scaledTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();

                Log.d("EventDispatchActivity", "onTouchEvent-->" + scaledTouchSlop + "  " + sqrt + " ");
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                velocityTracker.computeCurrentVelocity(30);
                Toast.makeText(this, "" + velocityTracker.getXVelocity(), Toast.LENGTH_SHORT).show();
                Log.e("EventDispatchActivity", "onTouchEvent-->velocityTracker" + velocityTracker.getXVelocity());

                break;
        }
        boolean resume = gestureDetector.onTouchEvent(event);
        return resume;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        velocityTracker.clear();
        velocityTracker.recycle();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


}

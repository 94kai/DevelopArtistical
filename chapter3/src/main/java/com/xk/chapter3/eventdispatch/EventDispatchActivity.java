package com.xk.chapter3.eventdispatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xk.chapter3.R;


///SampleViewGroup:dispatchTouchEvent-->
///SampleViewGroup:onInterceptTouchEvent-->
///SampleView:dispatchTouchEvent-->
///SampleView:onTouchEvent-->
///SampleViewGroup:onTouchEvent-->
//以上，viewgroup和view都没有消费事件，viewgroup也没有拦截，事件从activity产生，然后传递给window，
// 然后给viewgroup，viewgroup的dispatchTouchEvent就被调用，然后调用onInterceptTouchEvent验证是否拦截
//然后不拦截，就分发给view，调用dispatchTouchEvent，然后view没有孩子了，开始验证是否消费，调用onTouchEvent，
// return false，然后往回打->Viewgroup->window...就这样



//同一个事件序列是指 手指按下开始到手指抬起 down->move->...->up
public class EventDispatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdispatch);


    }


}

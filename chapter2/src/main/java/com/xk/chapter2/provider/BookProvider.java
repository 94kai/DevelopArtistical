package com.xk.chapter2.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 注意，内容观察者底层也是通过binder实现的，但是比aidl用起来简单，他仅仅是帮助我们实现了进程间通讯，具体的数据存储、查询之类的 还是需要我们自己完成的
 * 可以使用sqlite、sp、甚至文件 各种都无所谓，但是可能被多个进程、多个线程同时调用（想想通讯录），所以对于线程同步需要考虑，如果用的是一个sqlitehelper的话，系统以及实现了，总之视情况而定吧
 * <p>
 * <p>
 * oncreate是被系统调用的，运行在主线程。其他几个方法是运行在binder线程池中
 * Created by xuekai on 2017/9/16.
 */

public class BookProvider extends ContentProvider {

    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        //在匹配规则中添加两条规则：uri后面的path是book的时候，可以匹配为0，user时可以匹配为1,然后在增删改查中把uri转换成对应的0、1
        uriMatcher.addURI("com.xk.chapter2.provider.BookProvider", "book", 0);
        uriMatcher.addURI("com.xk.chapter2.provider.BookProvider", "user", 1);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int uriCode = uriMatcher.match(uri);
        String tableName = getTableName(uriCode);
        // TODO: by xk 2017/9/17 15:37 这里操作数据库
        Log.d("BookProvider", "query-->" + Thread.currentThread());
        return null;
    }
    @Override
    public boolean onCreate() {
        Log.d("BookProvider", "onCreate-->" + Thread.currentThread());
        return false;
    }



    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d("BookProvider", "getType-->" + Thread.currentThread());
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d("BookProvider", "insert-->" + Thread.currentThread());
        // TODO: by xk 2017/9/17 15:37 参照query，找到操作的表，然后存数数据
        getContext().getContentResolver().notifyChange(uri, null);//通知观察者，这个uri下的数据被改变了
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d("BookProvider", "delete-->" + Thread.currentThread());
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d("BookProvider", "update-->" + Thread.currentThread());
        return 0;
    }


    //除了增删改查，还可以通过call方法实现远程进程中方法的调用，这么一来不就是跟aidl实现的效果一样了吗，还很简单，来试一试（这里只是我瞎猜的写的一种使用办法，随机应变喽）(注意该方法是在binder线程池中，阻塞没关系，但是他的调用者——客户端就需要注意了)
    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        Bundle bundle = new Bundle();

        String[] as = arg.split("a");
        if (method.equals("add")) {
            int add = add(Integer.parseInt(as[0]), Integer.parseInt(as[1]));
            bundle.putInt("result", add);

        } else if (method.equals("sub")) {
            int add = sub(Integer.parseInt(as[0]), Integer.parseInt(as[1]));
            bundle.putInt("result", add);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bundle;
    }


    private int add(int a, int b) {
        return a + b;
    }

    private int sub(int a, int b) {
        return a - b;
    }

    /**
     * 通过uricode，转成表名
     *
     * @param uriCode
     * @return
     */
    public String getTableName(int uriCode) {
        switch (uriCode) {
            case 0:
                return "tb_book";
            case 1:
                return "tb_user";
        }
        return "";
    }
}

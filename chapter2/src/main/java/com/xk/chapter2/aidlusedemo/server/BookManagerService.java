package com.xk.chapter2.aidlusedemo.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xk.chapter2.aidlusedemo.Book;
import com.xk.chapter2.aidlusedemo.IBookManager;
import com.xk.chapter2.aidlusedemo.OnAddBookListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by xuekai on 2017/9/14.
 */

public class BookManagerService extends Service {
    private RemoteCallbackList<OnAddBookListener> listeners;

    private CopyOnWriteArrayList<Book> books;

    @Override
    public void onCreate() {
        super.onCreate();
        listeners = new RemoteCallbackList<>();//一般我们会在这里用arraylist，但是在多进程中，对象是无法传递的，对象的传递本质是序列化反序列化的过程，对象其实已经不是原来那个了。所以反注册就会失败。所以用这个类，可以解决这个问题。
        books = new CopyOnWriteArrayList<>();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IBookManager.Stub() {
            @Override
            public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {//在这里可以做权限校验
                if (code== IBinder.FIRST_CALL_TRANSACTION + 0) {
                    Log.d("BookManagerService","onTransact-->没有权限");
                    return false;
                }
                return super.onTransact(code, data, reply, flags);
            }

            @Override
            public List<Book> getBookList() throws RemoteException {
                Log.d("BookManagerService","getBookList-->"+listeners);
                return books;
            }

            @Override
            public void addBook(Book book) throws RemoteException{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                books.add(book);
                if (listeners != null) {
                    int i = listeners.beginBroadcast();
                    for (int i1 = 0; i1 < i; i1++) {
                        listeners.getBroadcastItem(i1).onBookAdd(book);
                    }
                    listeners.finishBroadcast();
                }
            }

            @Override
            public void registerOnAddBookListener(OnAddBookListener onAddBookListener) throws RemoteException {
                if (listeners != null) {
                     listeners.beginBroadcast();
                    listeners.register(onAddBookListener);
                    listeners.finishBroadcast();
                }
            }

            @Override
            public void unRegisterOnAddBookListener(OnAddBookListener onAddBookListener) throws RemoteException {
                if (listeners != null) {

                    listeners.beginBroadcast();//注意begain和finish要同时使用、配对使用
                    boolean unregister = listeners.unregister(onAddBookListener);
                    Log.d("BookManagerService","unRegisterOnAddBookListener-->"+unregister);
                    listeners.finishBroadcast();
                }
            }
        };
    }
}

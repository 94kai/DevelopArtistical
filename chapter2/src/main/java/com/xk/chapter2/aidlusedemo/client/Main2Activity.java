package com.xk.chapter2.aidlusedemo.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.xk.chapter2.R;
import com.xk.chapter2.aidlusedemo.Book;
import com.xk.chapter2.aidlusedemo.IBookManager;
import com.xk.chapter2.aidlusedemo.OnAddBookListener;
import com.xk.chapter2.aidlusedemo.server.BookManagerService;

import java.util.Calendar;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);

        bindService(new Intent(this, BookManagerService.class), conn, BIND_AUTO_CREATE);
    }

    private int bookId = 1;
    private int userid = 1;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                try {
                    iBookManager.addBook(new Book(Calendar.getInstance().get(Calendar.MILLISECOND) + "", bookId));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                bookId++;
                break;
            case R.id.btn2:
                try {
                    List<Book> bookList = iBookManager.getBookList();
                    Log.d("Main2Activity", "onClick-->" + bookList);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn3:
                try {
                    Log.d("Main2Activity","register-->"+o);
                    iBookManager.registerOnAddBookListener(o);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn4:
                try {
                    Log.d("Main2Activity","unregister-->"+o);
                    iBookManager.unRegisterOnAddBookListener(o);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private IBookManager iBookManager;


    private ServiceConnection conn = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBookManager = IBookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    OnAddBookListener o = new OnAddBookListener.Stub() {
        @Override
        public void onBookAdd(Book book) throws RemoteException {
            Log.d("Main2Activity", "onBookAdd-->接收到" + book);

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    //    @Override
//    public void onBookAdd(Book book) throws RemoteException {
//    }
//
//    @Override
//    public IBinder asBinder() {
//        return null;
//    }
}

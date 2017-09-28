package com.xk.chapter2.aidldemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.xk.chapter2.R;
import com.xk.chapter2.aidldemo.server.Book;
import com.xk.chapter2.aidldemo.server.IBookManager;
import com.xk.chapter2.aidldemo.server.IBookManagerStub;
import com.xk.chapter2.aidldemo.server.RemoteService;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IBookManager iBookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bindService(new Intent(this, RemoteService.class), conn, BIND_AUTO_CREATE);


    }


    public void add(View v)  {
        try {
            iBookManager.addBook(new Book(Calendar.getInstance().get(Calendar.MILLISECOND) + "", Calendar.getInstance().get(Calendar.MILLISECOND)));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getBook(View v) {
        List<Book> bookList = null;
        try {
            bookList = iBookManager.getBookList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.d("ProviderActivity", "getBook-->" + bookList);
    }

    ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBookManager = IBookManagerStub.asInterface(service);
//            service.linkToDeath(recipient,0);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


}

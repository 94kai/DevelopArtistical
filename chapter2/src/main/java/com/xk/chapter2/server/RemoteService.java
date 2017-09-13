package com.xk.chapter2.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xuekai on 2017/9/13.
 */

public class RemoteService extends Service {
    private List<Book> books = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IBookManagerStub() {
            @Override
            public List<Book> getBookList() {
                Log.d("RemoteService", "getBookList-->" + books);
                return books;
            }

            @Override
            public void addBook(Book book) {
                Log.d("RemoteService", "addBook-->" + book);
                books.add(book);
            }
        };
    }
}

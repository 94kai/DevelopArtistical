package com.xk.chapter2.aidldemo.server;

import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

/**
 * Created by xuekai on 2017/9/13.
 */

public interface IBookManager extends IInterface {
    int METHOD_ADDBOOK_ID = 0;
    int METHOD_GETBOOKLIST_ID = 1;

     String DESCRIPTION = "com.xk.chapter2.aidldemo.server.IBookManager";

    void addBook(Book book) throws RemoteException;

    List<Book> getBookList() throws RemoteException;
}

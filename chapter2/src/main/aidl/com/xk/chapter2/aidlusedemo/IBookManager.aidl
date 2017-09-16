// IBookManager.aidl
package com.xk.chapter2.aidlusedemo;

import com.xk.chapter2.aidlusedemo.Book;
import com.xk.chapter2.aidlusedemo.OnAddBookListener;
// Declare any non-default types here with import statements
interface IBookManager {
//    List<Book> getBookList();
    List<Book> getBookList();

    void addBook(in Book book);

    void registerOnAddBookListener(OnAddBookListener onAddBookListener);

    void unRegisterOnAddBookListener(OnAddBookListener onAddBookListener);

}


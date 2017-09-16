// OnAddBookListener.aidl
package com.xk.chapter2.aidlusedemo;

// Declare any non-default types here with import statements
import com.xk.chapter2.aidlusedemo.Book;
interface OnAddBookListener {
    void onBookAdd(in Book book);
}

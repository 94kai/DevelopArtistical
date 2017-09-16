package com.xk.chapter2.aidldemo.server;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xuekai on 2017/9/13.
 */

public class Book implements Parcelable {
    private String bookName;
    private int bookId;

    protected Book(Parcel in) {
        bookName = in.readString();
        bookId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookName);
        dest.writeInt(bookId);
    }

    public Book(String bookName, int bookId) {
        this.bookName = bookName;
        this.bookId = bookId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", bookId=" + bookId +
                '}';
    }
}

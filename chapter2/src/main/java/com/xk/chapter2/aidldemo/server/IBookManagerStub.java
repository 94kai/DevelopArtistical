package com.xk.chapter2.aidldemo.server;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;

/**
 * Created by xuekai on 2017/9/13.
 */

public abstract class IBookManagerStub extends Binder implements IBookManager {
    public IBookManagerStub() {
        this.attachInterface(this, IBookManager.DESCRIPTION);
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        Log.d("IBookManagerStub", "onTransact-->");
        if (code == METHOD_ADDBOOK_ID) {
            Book fromParcel = Book.CREATOR.createFromParcel(data);
            addBook(fromParcel);
            return true;
        } else if (code == METHOD_GETBOOKLIST_ID) {
            List<Book> bookList = getBookList();
            reply.writeTypedList(bookList);
            return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    public static IBookManager asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterface = iBinder.queryLocalInterface(IBookManager.DESCRIPTION);
        if (iInterface != null && iInterface instanceof IBookManager) {
            return (IBookManager) iInterface;
        }
        return new Proxy(iBinder);
    }


    @Override
    public void addBook(Book book) throws RemoteException {

    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        return null;
    }

    @Override
    public IBinder asBinder() {
        return this;
    }


    static class Proxy extends IBookManagerStub {
        //实质是BinderProxy
        IBinder mRemote;

        public Proxy(IBinder iBinder) {
            mRemote = iBinder;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Log.d("Proxy", "addBook-->" + book);
            Parcel _data = Parcel.obtain();//用来承载参数
            Parcel _reply = Parcel.obtain();//用来承载返回值
//            _data.writeInterfaceToken(DESCRIPTION);
            if (book != null) {
                _data.writeInt(1);
                book.writeToParcel(_data, 0);
            }
            mRemote.transact(METHOD_ADDBOOK_ID, _data, _reply, 0);
//            mRemote.transact(1,,null,1);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            Parcel _data = Parcel.obtain();//用来承载参数
            Parcel _reply = Parcel.obtain();//用来承载返回值
            mRemote.transact(METHOD_GETBOOKLIST_ID, _data, _reply, 0);
            return _reply.createTypedArrayList(Book.CREATOR);
        }
    }
}

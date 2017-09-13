//package com.xk.chapter2;
//
//import android.util.Log;
//
//public interface ICalculate extends android.os.IInterface {
//    /**
//     * Local-side IPC implementation stub class.
//     */
//    public static abstract class Stub extends android.os.Binder implements com.xk.chapter2.ICalculate {
//        private static final java.lang.String DESCRIPTOR = "com.xk.chapter2.ICalculate";
//
//        /**
//         * Construct the stub at attach it to the interface.
//         */
//        public Stub() {
//            this.attachInterface(this, DESCRIPTOR);
//        }
//
//        /**
//         * ICalculate是用来做计算功能的，那么他是在服务端的，他的实现（继承自Stub）也是在服务端的，而客户端需要调用它里面的方法，需要的就是这个接口对象，该方法的作用就是把Stub转换成可以在binder中传播的接口
//         * 如果客户端和服务的在同一进程中，那么可以共享数据，所以直接返回这个Stub就好了，如果不在同一个进程中，就创建一个代理对象Stub.Proxy，并返回
//         * Cast an IBinder object into an com.xk.chapter2.ICalculate interface,
//         * generating a proxy if needed.
//         */
//        public static com.xk.chapter2.ICalculate asInterface(android.os.IBinder obj) {
//            if ((obj == null)) {
//                return null;
//            }
//            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
//            if (((iin != null) && (iin instanceof com.xk.chapter2.ICalculate))) {
//                Log.d("Stub","asInterface-->走本地");
//                return ((com.xk.chapter2.ICalculate) iin);
//            }
//            Log.d("Stub","asInterface-->走远程");
//           return new com.xk.chapter2.ICalculate.Stub.Proxy(obj);
//        }
//
//        /**
//         * 返回当前binder对象
//         * @return
//         */
//        @Override
//        public android.os.IBinder asBinder() {
//            return this;
//        }
//
//        /**
//         * 该方法是服务端的方法（书上的话来说就是运行在Binder线程池，反正跟客户端不在一个进程中），只有在客户端、服务端不在一个进程的情况下才会走这里（如果在同一进程，asInterface会直接返回Stub对象，直接调用Stub里面的实现）
//         * 如果不在同一进程，asInterface会返回代理对象（Stub.Proxy），当客户端调用方法的时候，内部会像下面一样，把客户端的参数写入到_data,它是一个parcel（parcelable可知，这个东西可以binder中传输），然后还有一个code（code标记了调用的是接口中的哪个方法）
//         * 同时还有一个_reply,用来承载返回结果。然后_data、code、_reply等，这几个参数调用一个方法mRemote.transact(),很明显，这个是个远程方法，通过一系列底层实现，最后就会回调到服务端的这个onTransact方法（前面是在客户端，现在到了服务端了），然后服务端调用真实的方法实现，得到结果，写入
//         * 到_reply中，这里有个返回值，返回false，那么客户端的请求就会失败，可以用此来对方法权限做限制，毕竟不是所有的客户端都可以调用服务端的
//         * @param code
//         * @param data
//         * @param reply
//         * @param flags
//         * @return
//         * @throws android.os.RemoteException
//         */
//        @Override
//        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
//            switch (code) {
//                case INTERFACE_TRANSACTION: {
//                    reply.writeString(DESCRIPTOR);
//                    return true;
//                }
//                case TRANSACTION_plus: {
//                    data.enforceInterface(DESCRIPTOR);
//                    int _arg0;
//                    _arg0 = data.readInt();
//                    int _arg1;
//                    _arg1 = data.readInt();
//                    this.plus(_arg0, _arg1);
//                    reply.writeNoException();
//                    return true;
//                }
//                case TRANSACTION_sub: {
//                    data.enforceInterface(DESCRIPTOR);
//                    int _arg0;
//                    _arg0 = data.readInt();
//                    int _arg1;
//                    _arg1 = data.readInt();
//                    this.sub(_arg0, _arg1);
//                    reply.writeNoException();
//                    return true;
//                }
//            }
//            return super.onTransact(code, data, reply, flags);
//        }
//
//        private static class Proxy implements com.xk.chapter2.ICalculate {
//            private android.os.IBinder mRemote;
//
//            Proxy(android.os.IBinder remote) {
//                mRemote = remote;
//            }
//
//            @Override
//            public android.os.IBinder asBinder() {
//                return mRemote;
//            }
//
//            public java.lang.String getInterfaceDescriptor() {
//                return DESCRIPTOR;
//            }
//
//            @Override
//            public void plus(int a, int b) throws android.os.RemoteException {
//                android.os.Parcel _data = android.os.Parcel.obtain();
//                android.os.Parcel _reply = android.os.Parcel.obtain();
//                try {
//                    _data.writeInterfaceToken(DESCRIPTOR);
//                    _data.writeInt(a);
//                    _data.writeInt(b);
//                    mRemote.transact(Stub.TRANSACTION_plus, _data, _reply, 0);
//                    _reply.readException();
//                } finally {
//                    _reply.recycle();
//                    _data.recycle();
//                }
//            }
//
//            @Override
//            public void sub(int a, int b) throws android.os.RemoteException {
//                android.os.Parcel _data = android.os.Parcel.obtain();
//                android.os.Parcel _reply = android.os.Parcel.obtain();
//                try {
//                    _data.writeInterfaceToken(DESCRIPTOR);
//                    _data.writeInt(a);
//                    _data.writeInt(b);
//                    mRemote.transact(Stub.TRANSACTION_sub, _data, _reply, 0);
//                    _reply.readException();
//                } finally {
//                    _reply.recycle();
//                    _data.recycle();
//                }
//            }
//        }
//
//        static final int TRANSACTION_plus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
//        static final int TRANSACTION_sub = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
//    }
//
//    public void plus(int a, int b) throws android.os.RemoteException;
//
//    public void sub(int a, int b) throws android.os.RemoteException;
//}

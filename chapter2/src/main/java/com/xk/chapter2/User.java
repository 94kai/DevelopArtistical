//package com.xk.chapter2;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
///**
// * Created by xuekai on 2017/9/12.
// */
//
//public class User implements Parcelable {
//    private int age;
//    private String name;
//    private Home mHome;
//
//    protected User(Parcel in) {
//        age = in.readInt();
//        name = in.readString();
//        mHome = in.readParcelable(Home.class.getClassLoader());
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(age);
//        dest.writeString(name);
//        dest.writeParcelable(mHome, flags);
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    public static final Creator<User> CREATOR = new Creator<User>() {
//        @Override
//        public User createFromParcel(Parcel in) {
//            return new User(in);
//        }
//
//        @Override
//        public User[] newArray(int size) {
//            return new User[size];
//        }
//    };
//}

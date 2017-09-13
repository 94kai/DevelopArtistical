//package com.xk.chapter2;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
///**
// * Created by xuekai on 2017/9/12.
// */
//
//public class Home implements Parcelable{
//    private int length;
//    private String homeName;
//
//    protected Home(Parcel in) {
//        length = in.readInt();
//        homeName = in.readString();
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(length);
//        dest.writeString(homeName);
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    public static final Creator<Home> CREATOR = new Creator<Home>() {
//        @Override
//        public Home createFromParcel(Parcel in) {
//            return new Home(in);
//        }
//
//        @Override
//        public Home[] newArray(int size) {
//            return new Home[size];
//        }
//    };
//}

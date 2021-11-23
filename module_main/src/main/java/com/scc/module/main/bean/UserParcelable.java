package com.scc.module.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class UserParcelable<T extends Parcelable> implements Parcelable {
    private String name;
    private int age;
    private T data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public UserParcelable(String name, int age, T data) {
        this.name = name;
        this.age = age;
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeString(data.getClass().getName());
        dest.writeParcelable(this.data, flags);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.age = source.readInt();
        String dataName = source.readString();
        try {
            this.data = source.readParcelable(Class.forName(dataName).getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected UserParcelable(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
        String dataName = in.readString();
        try {
            this.data = in.readParcelable(Class.forName(dataName).getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static final Creator<UserParcelable> CREATOR = new Creator<UserParcelable>() {
        @Override
        public UserParcelable createFromParcel(Parcel source) {
            return new UserParcelable(source);
        }

        @Override
        public UserParcelable[] newArray(int size) {
            return new UserParcelable[size];
        }
    };
}

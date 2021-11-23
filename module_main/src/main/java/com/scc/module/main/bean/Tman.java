package com.scc.module.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Tman implements Parcelable {
    String color;

    public Tman(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.color);
    }

    public void readFromParcel(Parcel source) {
        this.color = source.readString();
    }

    protected Tman(Parcel in) {
        this.color = in.readString();
    }

    public static final Creator<Tman> CREATOR = new Creator<Tman>() {
        @Override
        public Tman createFromParcel(Parcel source) {
            return new Tman(source);
        }

        @Override
        public Tman[] newArray(int size) {
            return new Tman[size];
        }
    };
}

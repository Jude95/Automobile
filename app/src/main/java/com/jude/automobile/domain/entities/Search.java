package com.jude.automobile.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class Search implements Serializable, Parcelable {
    public static final int TYPE_BRAND = 1;
    public static final int TYPE_LINE = 2;
    public static final int TYPE_TYPE = 3;


    int type;
    String word;
    String info;

    public Search(int type,String word) {
        this.word = word;
        this.type = type;
    }

    public Search( int type, String word,String info) {
        this.info = info;
        this.type = type;
        this.word = word;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Search){
            return ((Search) o).type == type&&((Search) o).word.equals(word);
        }
        return false;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeString(this.word);
        dest.writeString(this.info);
    }

    protected Search(Parcel in) {
        this.type = in.readInt();
        this.word = in.readString();
        this.info = in.readString();
    }

    public static final Parcelable.Creator<Search> CREATOR = new Parcelable.Creator<Search>() {
        public Search createFromParcel(Parcel source) {
            return new Search(source);
        }

        public Search[] newArray(int size) {
            return new Search[size];
        }
    };
}

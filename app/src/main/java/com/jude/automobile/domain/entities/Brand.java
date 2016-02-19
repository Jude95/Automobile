package com.jude.automobile.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class Brand implements Serializable, Parcelable {
    int id;
    String name;
    String avatar;
    String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.avatar);
        dest.writeString(this.word);
    }

    public Brand() {
    }

    protected Brand(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.avatar = in.readString();
        this.word = in.readString();
    }

    public static final Parcelable.Creator<Brand> CREATOR = new Parcelable.Creator<Brand>() {
        public Brand createFromParcel(Parcel source) {
            return new Brand(source);
        }

        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };
}

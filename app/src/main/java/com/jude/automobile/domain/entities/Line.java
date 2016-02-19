package com.jude.automobile.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class Line implements Serializable, Parcelable {
    int id;
    @SerializedName("vendor_id")
    int vendorId;
    @SerializedName("vendor_name")
    String vendorName;
    String name;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    String word;

    public Line() {
    }

    public Line(int id, int VendorId, String vendorName, String name) {
        this.id = id;
        this.vendorId = VendorId;
        this.vendorName = vendorName;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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
        dest.writeInt(this.vendorId);
        dest.writeString(this.vendorName);
        dest.writeString(this.name);
        dest.writeString(this.word);
    }

    protected Line(Parcel in) {
        this.id = in.readInt();
        this.vendorId = in.readInt();
        this.vendorName = in.readString();
        this.name = in.readString();
        this.word = in.readString();
    }

    public static final Parcelable.Creator<Line> CREATOR = new Parcelable.Creator<Line>() {
        public Line createFromParcel(Parcel source) {
            return new Line(source);
        }

        public Line[] newArray(int size) {
            return new Line[size];
        }
    };
}

package com.jude.automobile.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhuchenxi on 16/2/19.
 */
public class Vendor implements Serializable, Parcelable {
    private int id;
    private String name;
    @SerializedName("brand_id")
    private int brandId;

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
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
        dest.writeInt(this.brandId);
    }

    public Vendor() {
    }

    protected Vendor(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.brandId = in.readInt();
    }

    public static final Parcelable.Creator<Vendor> CREATOR = new Parcelable.Creator<Vendor>() {
        public Vendor createFromParcel(Parcel source) {
            return new Vendor(source);
        }

        public Vendor[] newArray(int size) {
            return new Vendor[size];
        }
    };
}

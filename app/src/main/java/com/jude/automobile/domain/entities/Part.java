package com.jude.automobile.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class Part implements Serializable, Parcelable {
    int id;
    String type;
    String brand;
    @SerializedName("drawing_number")
    String drawingNumber;
    String avatar;
    String note;
    List<String> picture;
    @SerializedName("picture_full")
    List<ImageInfo> pictureFull;

    public Part() {
    }

    public Part(String avatar, String brand, String drawingNumber, int id, List<String> picture, String type, String assembleNote) {
        this.avatar = avatar;
        this.brand = brand;
        this.drawingNumber = drawingNumber;
        this.id = id;
        this.picture = picture;
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDrawingNumber() {
        return drawingNumber;
    }

    public void setDrawingNumber(String drawingNumber) {
        this.drawingNumber = drawingNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ImageInfo> getPictureFull() {
        return pictureFull;
    }

    public void setPictureFull(List<ImageInfo> pictureFull) {
        this.pictureFull = pictureFull;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.type);
        dest.writeString(this.brand);
        dest.writeString(this.drawingNumber);
        dest.writeString(this.avatar);
        dest.writeString(this.note);
        dest.writeStringList(this.picture);
        dest.writeTypedList(pictureFull);
    }

    protected Part(Parcel in) {
        this.id = in.readInt();
        this.type = in.readString();
        this.brand = in.readString();
        this.drawingNumber = in.readString();
        this.avatar = in.readString();
        this.note = in.readString();
        this.picture = in.createStringArrayList();
        this.pictureFull = in.createTypedArrayList(ImageInfo.CREATOR);
    }

    public static final Parcelable.Creator<Part> CREATOR = new Parcelable.Creator<Part>() {
        public Part createFromParcel(Parcel source) {
            return new Part(source);
        }

        public Part[] newArray(int size) {
            return new Part[size];
        }
    };
}

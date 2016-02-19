package com.jude.automobile.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhuchenxi on 16/2/18.
 */
public class Assemble implements Serializable, Parcelable {
    @SerializedName("id")
    int id;
    @SerializedName("note")
    String note;

    @SerializedName("model_id")
    int typeId;
    @SerializedName("model_name")
    String typeName;

    @SerializedName("part_id")
    int partId;
    @SerializedName("part_type")
    String partType;
    @SerializedName("part_brand")
    String partBrand;
    @SerializedName("part_drawing_number")
    String partDrawingNumber;
    @SerializedName("part_avatar")
    String partAvatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPartAvatar() {
        return partAvatar;
    }

    public void setPartAvatar(String partAvatar) {
        this.partAvatar = partAvatar;
    }

    public String getPartBrand() {
        return partBrand;
    }

    public void setPartBrand(String partBrand) {
        this.partBrand = partBrand;
    }

    public String getPartDrawingNumber() {
        return partDrawingNumber;
    }

    public void setPartDrawingNumber(String partDrawingNumber) {
        this.partDrawingNumber = partDrawingNumber;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.note);
        dest.writeInt(this.typeId);
        dest.writeString(this.typeName);
        dest.writeInt(this.partId);
        dest.writeString(this.partType);
        dest.writeString(this.partBrand);
        dest.writeString(this.partDrawingNumber);
        dest.writeString(this.partAvatar);
    }

    public Assemble() {
    }

    protected Assemble(Parcel in) {
        this.id = in.readInt();
        this.note = in.readString();
        this.typeId = in.readInt();
        this.typeName = in.readString();
        this.partId = in.readInt();
        this.partType = in.readString();
        this.partBrand = in.readString();
        this.partDrawingNumber = in.readString();
        this.partAvatar = in.readString();
    }

    public static final Parcelable.Creator<Assemble> CREATOR = new Parcelable.Creator<Assemble>() {
        public Assemble createFromParcel(Parcel source) {
            return new Assemble(source);
        }

        public Assemble[] newArray(int size) {
            return new Assemble[size];
        }
    };
}

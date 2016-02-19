package com.jude.automobile.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuchenxi on 16/2/2.
 */
public class ConstantParams implements Serializable,Cloneable, Parcelable {
    List<String> structure;
    List<String> drive;
    List<String> fuel;
    @SerializedName("fuel_feed")
    List<String> fuelFeed;
    List<String> engine;
    List<String> part;

    public List<String> getDrive() {
        return drive;
    }

    public void setDrive(List<String> drive) {
        this.drive = drive;
    }

    public List<String> getEngine() {
        return engine;
    }

    public void setEngine(List<String> engine) {
        this.engine = engine;
    }

    public List<String> getFuelFeed() {
        return fuelFeed;
    }

    public void setFuelFeed(List<String> fuelFeed) {
        this.fuelFeed = fuelFeed;
    }

    public List<String> getStructure() {
        return structure;
    }

    public void setStructure(List<String> structure) {
        this.structure = structure;
    }

    public List<String> getFuel() {
        return fuel;
    }

    public void setFuel(List<String> fuel) {
        this.fuel = fuel;
    }

    public List<String> getPart() {
        return part;
    }

    public void setPart(List<String> part) {
        this.part = part;
    }

    @Override
    public ConstantParams clone() {
        ConstantParams params = new ConstantParams();
        params.setStructure((List<String>) new ArrayList<>(getStructure()).clone());
        params.setDrive((List<String>) new ArrayList<>(getDrive()).clone());
        params.setEngine((List<String>) new ArrayList<>(getEngine()).clone());
        params.setFuelFeed((List<String>) new ArrayList<>(getFuelFeed()).clone());
        params.setFuel((List<String>) new ArrayList<>(getFuel()).clone());
        params.setPart((List<String>) new ArrayList<>(getPart()).clone());
        return params;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.structure);
        dest.writeStringList(this.drive);
        dest.writeStringList(this.fuel);
        dest.writeStringList(this.fuelFeed);
        dest.writeStringList(this.engine);
        dest.writeStringList(this.part);
    }

    public ConstantParams() {
    }

    protected ConstantParams(Parcel in) {
        this.structure = in.createStringArrayList();
        this.drive = in.createStringArrayList();
        this.fuel = in.createStringArrayList();
        this.fuelFeed = in.createStringArrayList();
        this.engine = in.createStringArrayList();
        this.part = in.createStringArrayList();
    }

    public static final Parcelable.Creator<ConstantParams> CREATOR = new Parcelable.Creator<ConstantParams>() {
        public ConstantParams createFromParcel(Parcel source) {
            return new ConstantParams(source);
        }

        public ConstantParams[] newArray(int size) {
            return new ConstantParams[size];
        }
    };
}

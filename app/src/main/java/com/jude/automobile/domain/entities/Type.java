package com.jude.automobile.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class Type implements Serializable, Parcelable {
    int id;
    @SerializedName("line_id")
    int lineId;
    String name;
    @SerializedName("line_name")
    String lineName;
    String power;
    String displacement;
    String cylinders;//汽缸
    String valve;//阀门
    String structure;
    String drive;//驱动方式
    String engine;
    String fuel;
    @SerializedName("fuel_feed")
    String fuelFeed;
    String tecdoc;
    @SerializedName("engine_code")
    String engineCode;
    String time;
    @SerializedName("displacement_tech")
    String displacementTech;
    String word;


    public Type() {
    }

    public Type(String cylinders, String displacement, String displacement_tech, String drive, String engine, String engine_code, String fuel, String fuel_feed, int id, String name, String power, String structure, String tecdoc, String time, int type_id, String lineName, String valve) {
        this.cylinders = cylinders;
        this.displacement = displacement;
        this.displacementTech = displacement_tech;
        this.drive = drive;
        this.engine = engine;
        this.engineCode = engine_code;
        this.fuel = fuel;
        this.fuelFeed = fuel_feed;
        this.id = id;
        this.name = name;
        this.power = power;
        this.structure = structure;
        this.tecdoc = tecdoc;
        this.time = time;
        this.lineId = type_id;
        this.lineName = lineName;
        this.valve = valve;
    }

    public String getCylinders() {
        return cylinders;
    }

    public void setCylinders(String cylinders) {
        this.cylinders = cylinders;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getDisplacementTech() {
        return displacementTech;
    }

    public void setDisplacementTech(String displacementTech) {
        this.displacementTech = displacementTech;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getEngineCode() {
        return engineCode;
    }

    public void setEngineCode(String engineCode) {
        this.engineCode = engineCode;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getFuelFeed() {
        return fuelFeed;
    }

    public void setFuelFeed(String fuelFeed) {
        this.fuelFeed = fuelFeed;
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

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getTecdoc() {
        return tecdoc;
    }

    public void setTecdoc(String tecdoc) {
        this.tecdoc = tecdoc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getValve() {
        return valve;
    }

    public void setValve(String valve) {
        this.valve = valve;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.lineId);
        dest.writeString(this.name);
        dest.writeString(this.lineName);
        dest.writeString(this.power);
        dest.writeString(this.displacement);
        dest.writeString(this.cylinders);
        dest.writeString(this.valve);
        dest.writeString(this.structure);
        dest.writeString(this.drive);
        dest.writeString(this.engine);
        dest.writeString(this.fuel);
        dest.writeString(this.fuelFeed);
        dest.writeString(this.tecdoc);
        dest.writeString(this.engineCode);
        dest.writeString(this.time);
        dest.writeString(this.displacementTech);
        dest.writeString(this.word);
    }

    protected Type(Parcel in) {
        this.id = in.readInt();
        this.lineId = in.readInt();
        this.name = in.readString();
        this.lineName = in.readString();
        this.power = in.readString();
        this.displacement = in.readString();
        this.cylinders = in.readString();
        this.valve = in.readString();
        this.structure = in.readString();
        this.drive = in.readString();
        this.engine = in.readString();
        this.fuel = in.readString();
        this.fuelFeed = in.readString();
        this.tecdoc = in.readString();
        this.engineCode = in.readString();
        this.time = in.readString();
        this.displacementTech = in.readString();
        this.word = in.readString();
    }

    public static final Parcelable.Creator<Type> CREATOR = new Parcelable.Creator<Type>() {
        public Type createFromParcel(Parcel source) {
            return new Type(source);
        }

        public Type[] newArray(int size) {
            return new Type[size];
        }
    };
}

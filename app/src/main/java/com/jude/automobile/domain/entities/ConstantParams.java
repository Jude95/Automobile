package com.jude.automobile.domain.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuchenxi on 16/2/2.
 */
public class ConstantParams implements Serializable,Cloneable{
    List<String> structure;
    List<String> drive;
    List<String> fuel;
    @SerializedName("fuel_feed")
    List<String> fuelFeed;
    List<String> engine;

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

    @Override
    public ConstantParams clone() {
        ConstantParams params = new ConstantParams();
        params.setStructure((List<String>) new ArrayList<>(getStructure()).clone());
        params.setDrive((List<String>) new ArrayList<>(getDrive()).clone());
        params.setEngine((List<String>) new ArrayList<>(getEngine()).clone());
        params.setFuelFeed((List<String>) new ArrayList<>(getFuelFeed()).clone());
        params.setFuel((List<String>) new ArrayList<>(getFuel()).clone());
        return params;
    }
}

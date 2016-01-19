package com.jude.automobile.domain.entities;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class Model {
    int id;
    int type_id;
    String name;
    String typeName;
    String power;
    String displacement;
    String cylinders;//汽缸
    String valve;//阀门
    String structure;
    String drive;//驱动方式
    String engine;
    String fuel;
    String fuelFeed;
    String tecdoc;
    String engineCode;
    String time;
    String displacementTech;

    public Model(String cylinders, String displacement, String displacement_tech, String drive, String engine, String engine_code, String fuel, String fuel_feed, int id, String name, String power, String structure, String tecdoc, String time, int type_id, String typeName, String valve) {
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
        this.type_id = type_id;
        this.typeName = typeName;
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

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getValve() {
        return valve;
    }

    public void setValve(String valve) {
        this.valve = valve;
    }
}

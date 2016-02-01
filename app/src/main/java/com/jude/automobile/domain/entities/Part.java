package com.jude.automobile.domain.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class Part implements Serializable {
    int id;
    String type;
    String brand;
    String drawingNumber;
    String avatar;
    List<String> picture;
    String note;

    public Part(String avatar, String brand, String drawingNumber, int id, List<String> picture, String type,String note) {
        this.avatar = avatar;
        this.brand = brand;
        this.drawingNumber = drawingNumber;
        this.id = id;
        this.picture = picture;
        this.type = type;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

package com.jude.automobile.domain.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhuchenxi on 16/1/19.
 */
public class Part implements Serializable {
    int id;
    String type;
    String brand;
    @SerializedName("drawing_number")
    String drawingNumber;
    String avatar;
    List<String> picture;
    @SerializedName("picture_full")
    List<ImageInfo> pictureFull;

    //绑定信息
    @SerializedName("model_id")
    int modelId;
    @SerializedName("model_name")
    String modelName;
    @SerializedName("assemble_id")
    int assembleId;
    @SerializedName("assemble_note")
    String assembleNote;

    public Part() {
    }

    public Part(String avatar, String brand, String drawingNumber, int id, List<String> picture, String type, String assembleNote) {
        this.avatar = avatar;
        this.brand = brand;
        this.drawingNumber = drawingNumber;
        this.id = id;
        this.picture = picture;
        this.type = type;
        this.assembleNote = assembleNote;
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

    public String getAssembleNote() {
        return assembleNote;
    }

    public void setAssembleNote(String assembleNote) {
        this.assembleNote = assembleNote;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getAssembleId() {
        return assembleId;
    }

    public void setAssembleId(int assembleId) {
        this.assembleId = assembleId;
    }

    public List<ImageInfo> getPictureFull() {
        return pictureFull;
    }

    public void setPictureFull(List<ImageInfo> pictureFull) {
        this.pictureFull = pictureFull;
    }

}

package com.jude.automobile.domain.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class Type implements Serializable {
    int id;
    @SerializedName("line_id")
    int lineId;
    @SerializedName("line_name")
    String lineName;
    @SerializedName("line_avatar")
    String lineAvatar;
    String name;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    String word;


    public String getLineAvatar() {
        return lineAvatar;
    }

    public void setLineAvatar(String lineAvatar) {
        this.lineAvatar = lineAvatar;
    }


    public Type() {
    }

    public Type(int id, int lineId, String lineName, String name) {
        this.id = id;
        this.lineId = lineId;
        this.lineName = lineName;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

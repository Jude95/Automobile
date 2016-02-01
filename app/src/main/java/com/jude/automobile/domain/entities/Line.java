package com.jude.automobile.domain.entities;

import java.io.Serializable;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class Line implements Serializable{
    int id;
    String name;
    String avatar;
    String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
}

package com.jude.automobile.domain.entities;

/**
 * Created by zhuchenxi on 16/1/18.
 */
public class Line {
    int id;
    String name;
    String avatar;

    public Line(int id,String avatar, String name) {
        this.avatar = avatar;
        this.id = id;
        this.name = name;
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

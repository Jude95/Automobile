package com.jude.automobile.domain.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mr.Jude on 2016/2/24.
 */
public class UpdateInfo {
    String info;
    @SerializedName("version_code")
    int versionCode;
    @SerializedName("version_name")
    String versionName;
    String address;
    long date;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}

package com.jude.automobile.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhuchenxi on 16/1/21.
 */
public class Account implements Serializable, Parcelable {
    int id;
    String account;
    String name;
    @SerializedName("service_begin")
    long serviceBegin;
    String token;
    int manager;

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public Account(int id, String name, String account, String token) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getServiceBegin() {
        return serviceBegin;
    }

    public void setServiceBegin(long serviceBegin) {
        this.serviceBegin = serviceBegin;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Account){
            return token.equals(((Account) o).getToken())&&id == ((Account) o).id&& account.equals(((Account) o).account)&&name.equals(((Account) o).name)&&serviceBegin==((Account) o).serviceBegin&&manager == ((Account) o).manager;
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.account);
        dest.writeString(this.name);
        dest.writeLong(this.serviceBegin);
        dest.writeString(this.token);
        dest.writeInt(this.manager);
    }

    protected Account(Parcel in) {
        this.id = in.readInt();
        this.account = in.readString();
        this.name = in.readString();
        this.serviceBegin = in.readLong();
        this.token = in.readString();
        this.manager = in.readInt();
    }

    public static final Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        public Account createFromParcel(Parcel source) {
            return new Account(source);
        }

        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
}

package com.jude.automobile.domain.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhuchenxi on 16/1/21.
 */
public class Account implements Serializable{
    int id;
    String account;
    String name;
    @SerializedName("service_begin")
    long serviceBegin;
    String token;
    boolean manager;

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
    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Account){
            return id == ((Account) o).id&& account.equals(((Account) o).account)&&name.equals(((Account) o).name)&&serviceBegin==((Account) o).serviceBegin&&manager == ((Account) o).isManager();
        }
        return false;
    }
}

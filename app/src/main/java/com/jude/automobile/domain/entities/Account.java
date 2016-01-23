package com.jude.automobile.domain.entities;

/**
 * Created by zhuchenxi on 16/1/21.
 */
public class Account {
    int id;
    String number;
    String name;
    boolean activity;
    String token;

    public Account(boolean activity, int id, String name, String number, String token) {
        this.activity = activity;
        this.id = id;
        this.name = name;
        this.number = number;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Account){
            return id == ((Account) o).id&&number.equals(((Account) o).number)&&name.equals(((Account) o).name)&&activity==((Account) o).activity;
        }
        return false;
    }
}

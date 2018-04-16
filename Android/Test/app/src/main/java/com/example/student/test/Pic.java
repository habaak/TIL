package com.example.student.test;

import java.util.Date;

public class Pic {
    String name;
    String date;
    String disc;
    int resId;

    public Pic(){

    }
    public Pic(String name, String date, String disc, int resId) {
        this.name = name;
        this.date = date;
        this.disc = disc;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}

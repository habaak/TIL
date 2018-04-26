package com.example.habaa.playground;

/**
 * Created by wlwl0 on 2018-04-23.
 */

public class Contents {
    String cmt;
    int pointId;
    //int resId;
    String imgUrl;

    public Contents(){

    }
    public Contents(String cmt, int pointId, String imgUrl) {
        this.cmt = cmt;
        this.pointId = pointId;
        //this.resId = resId;
        this.imgUrl = imgUrl;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    /*public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }*/

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

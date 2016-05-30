package com.example.lw_pc.tribblekill.model;

import java.util.Observable;

/**
 * 登录用户 info
 * Created by LW-PC on 2016/5/30.
 */
public class Info extends Observable {
    private String mImage_url;
    private String mName;

    public void setValue(String image_url, String name) {
        mImage_url = image_url;
        mName = name;
        setChanged();
    }

    public String getmImage_url() {
        return mImage_url;
    }

    public String getmName() {
        return mName;
    }

}

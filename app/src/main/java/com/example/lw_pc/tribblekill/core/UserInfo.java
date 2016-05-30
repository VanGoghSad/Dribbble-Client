package com.example.lw_pc.tribblekill.core;

import com.example.lw_pc.tribblekill.model.Info;

/**
 * User info 单例模式
 * Created by LW-PC on 2016/5/30.
 */
public class UserInfo {
    private Info info;
    private volatile static UserInfo instance;

    private UserInfo() {
        info = new Info();
    }

    public static UserInfo getInstance() {
        if (instance == null) {
            synchronized (UserInfo.class) {
                if (instance == null) {
                    instance = new UserInfo();
                }
            }
        }
        return instance;
    }
    public Info getInfo() {
        return info;
    }



}

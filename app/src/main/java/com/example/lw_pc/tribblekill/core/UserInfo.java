package com.example.lw_pc.tribblekill.core;

import com.example.lw_pc.tribblekill.model.Login;
import com.example.lw_pc.tribblekill.model.Shot;
import com.example.lw_pc.tribblekill.model.User;

/**
 * User info 单例模式
 * Created by LW-PC on 2016/5/30.
 */
public class UserInfo {
    private Shot info;
    private Login out;
    private volatile static UserInfo instance;

    private UserInfo() {
        info = new Shot();
        out = new Login();
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
    public Shot getInfo() {
        return info;
    }

    public Login getOut() {
        return out;
    }





}

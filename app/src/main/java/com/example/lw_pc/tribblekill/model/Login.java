package com.example.lw_pc.tribblekill.model;

import java.io.Serializable;
import java.util.Observable;

/**
 * Created by LW-PC on 2016/6/1.
 */
public class Login extends Observable implements Serializable{
    private boolean isLogin;

    public void setValue(boolean b) {
        this.isLogin = b;
        setChanged();
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}

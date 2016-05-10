package com.example.lw_pc.tribblekill.model;


import java.io.Serializable;

/**
 * Created by LW-PC on 2016/5/4.
 * 记录点赞comment
 */
public class Like implements Serializable {

    /**
     * id : 24400091
     * created_at : 2014-01-06T17:19:59Z
     */

    private int id;
    private String created_at;
 /*   private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}

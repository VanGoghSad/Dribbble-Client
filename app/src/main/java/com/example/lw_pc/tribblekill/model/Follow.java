package com.example.lw_pc.tribblekill.model;

import java.io.Serializable;

/**
 * Created by LW-PC on 2016/5/22.
 */
public class Follow implements Serializable {
    private int id;
    private String created_at;
    private Follower follower;

    public Follower getFollower() {
        return follower;
    }

    public void setFollower(Follower follower) {
        this.follower = follower;
    }

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

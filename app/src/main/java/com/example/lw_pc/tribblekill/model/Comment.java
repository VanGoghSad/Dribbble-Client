package com.example.lw_pc.tribblekill.model;

import java.io.Serializable;

/**
 * Created by LW-PC on 2016/4/26.
 */
public class Comment implements Serializable {

    /**
     * id : 5216913
     * body : <p>Love the simplicity and color palette. Who would have thunk clouds could be rendered that way. Awesome work man!</p>
     * likes_count : 2
     * likes_url : https://api.dribbble.com/v1/shots/2673544/comments/5216913/likes
     * created_at : 2016-04-25T16:01:52Z
     * updated_at : 2016-04-25T16:01:52Z
     */

    private int id;
    private String body;
    private int likes_count;
    private String likes_url;
    private String created_at;
    private String updated_at;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public String getLikes_url() {
        return likes_url;
    }

    public void setLikes_url(String likes_url) {
        this.likes_url = likes_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}

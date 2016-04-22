package com.example.lw_pc.tribblekill.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LW-PC on 2016/4/11.
 */
public class Shot implements Serializable{

    /**
     * id : 2643991
     * title : Workflow board UX
     * description : null
     * width : 400
     * height : 300
     * images : {"hidpi":"https://d13yacurqjgara.cloudfront.net/users/32512/screenshots/2643991/tacoui.png","normal":"https://d13yacurqjgara.cloudfront.net/users/32512/screenshots/2643991/tacoui_1x.png","teaser":"https://d13yacurqjgara.cloudfront.net/users/32512/screenshots/2643991/tacoui_teaser.png"}
     * views_count : 3984
     * likes_count : 267
     * comments_count : 12
     * attachments_count : 0
     * rebounds_count : 0
     * buckets_count : 31
     * created_at : 2016-04-11T03:48:06Z
     * updated_at : 2016-04-11T03:49:43Z
     * html_url : https://dribbble.com/shots/2643991-Workflow-board-UX
     * attachments_url : https://api.dribbble.com/v1/shots/2643991/attachments
     * buckets_url : https://api.dribbble.com/v1/shots/2643991/buckets
     * comments_url : https://api.dribbble.com/v1/shots/2643991/comments
     * likes_url : https://api.dribbble.com/v1/shots/2643991/likes
     * projects_url : https://api.dribbble.com/v1/shots/2643991/projects
     * rebounds_url : https://api.dribbble.com/v1/shots/2643991/rebounds
     * animated : false
     * tags : ["app","cards","chat","concept","ios","messages","saas","social","storage","structure","ui","ux"]
     */

    private int id;
    private String title;
    private String description;
    private int width;
    private int height;
    /**
     * hidpi : https://d13yacurqjgara.cloudfront.net/users/32512/screenshots/2643991/tacoui.png
     * normal : https://d13yacurqjgara.cloudfront.net/users/32512/screenshots/2643991/tacoui_1x.png
     * teaser : https://d13yacurqjgara.cloudfront.net/users/32512/screenshots/2643991/tacoui_teaser.png
     */

    private ImagesBean images;
    private int views_count;
    private int likes_count;
    private int comments_count;
    private int attachments_count;
    private int rebounds_count;
    private int buckets_count;
    private String created_at;
    private String updated_at;
    private String html_url;
    private String attachments_url;
    private String buckets_url;
    private String comments_url;
    private String likes_url;
    private String projects_url;
    private String rebounds_url;
    private boolean animated;
    private List<String> tags;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getAttachments_count() {
        return attachments_count;
    }

    public void setAttachments_count(int attachments_count) {
        this.attachments_count = attachments_count;
    }

    public int getRebounds_count() {
        return rebounds_count;
    }

    public void setRebounds_count(int rebounds_count) {
        this.rebounds_count = rebounds_count;
    }

    public int getBuckets_count() {
        return buckets_count;
    }

    public void setBuckets_count(int buckets_count) {
        this.buckets_count = buckets_count;
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

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getAttachments_url() {
        return attachments_url;
    }

    public void setAttachments_url(String attachments_url) {
        this.attachments_url = attachments_url;
    }

    public String getBuckets_url() {
        return buckets_url;
    }

    public void setBuckets_url(String buckets_url) {
        this.buckets_url = buckets_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getLikes_url() {
        return likes_url;
    }

    public void setLikes_url(String likes_url) {
        this.likes_url = likes_url;
    }

    public String getProjects_url() {
        return projects_url;
    }

    public void setProjects_url(String projects_url) {
        this.projects_url = projects_url;
    }

    public String getRebounds_url() {
        return rebounds_url;
    }

    public void setRebounds_url(String rebounds_url) {
        this.rebounds_url = rebounds_url;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public static class ImagesBean implements Serializable{
        private String hidpi;
        private String normal;
        private String teaser;

        public String getHidpi() {
            return hidpi;
        }

        public void setHidpi(String hidpi) {
            this.hidpi = hidpi;
        }

        public String getNormal() {
            return normal;
        }

        public void setNormal(String normal) {
            this.normal = normal;
        }

        public String getTeaser() {
            return teaser;
        }

        public void setTeaser(String teaser) {
            this.teaser = teaser;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

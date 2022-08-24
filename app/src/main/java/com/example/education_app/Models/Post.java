package com.example.education_app.Models;

import com.google.firebase.database.ServerValue;

public class Post {


    private String postKey;
    private String title;
    private String description;
    private String picture;
    private String userId;
    //private String userPhoto;
    private Object timeStamp ;


    public Post(String title, String description, String picture, String userId) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.userId = userId;
        //this.userPhoto = userPhoto;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    // make sure to have an empty constructor inside ur model class
    public Post() {
    }


    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public String getUserId() {
        return userId;
    }


    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}

package com.example.education_app;

import com.google.firebase.database.ServerValue;

public class Comment {
    private String contents,userid,image,urname;
    private Object timestamp;


    public Comment() {
    }

    public Comment(String contents, String userid, String image, String urname) {
        this.contents = contents;
        this.userid = userid;
        this.image = image;
        this.urname = urname;
        this.timestamp = ServerValue.TIMESTAMP;

    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrname() {
        return urname;
    }

    public void setUrname(String urname) {
        this.urname = urname;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }
}

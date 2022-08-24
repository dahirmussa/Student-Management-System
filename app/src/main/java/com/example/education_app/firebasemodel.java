package com.example.education_app;

public class firebasemodel {

    String emails;
    String userprofile;
    String uid;
    String status;


    public firebasemodel(String emails, String userprofile, String uid, String status) {
        this.emails = emails;
        this.userprofile = userprofile;
        this.uid = uid;
        this.status = status;
    }

    public firebasemodel(){

    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getUserprofile() {
        return userprofile;
    }

    public void setUserprofile(String userprofile) {
        this.userprofile = userprofile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.example.education_app;

public class MainModel {

    private String emails, password, roles,userprofile;

    public MainModel(){

    }

    public MainModel(String emails, String password, String roles, String userprofile) {
        this.emails = emails;
        this.password = password;
        this.roles = roles;
        this.userprofile = userprofile;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUserprofile() {
        return userprofile;
    }

    public void setUserprofile(String userprofile) {
        this.userprofile = userprofile;
    }
}

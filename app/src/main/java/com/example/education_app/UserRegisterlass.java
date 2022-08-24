package com.example.education_app;

public class UserRegisterlass
{
    String Emails, Password,roles, userprofile, uid; //userstatus;

    public UserRegisterlass(){

    }

    public UserRegisterlass(String emails, String password, String roles, String userprofile, String uid) {
        Emails = emails;
        Password = password;
        this.roles = roles;
        this.userprofile = userprofile;
        this.uid = uid;
      //  this.userstatus = userstatus;
    }

    public String getUserprofile() {
        return userprofile;
    }

    public void setUserprofile(String userprofile) {
        this.userprofile = userprofile;
    }

    public String getEmails() {
        return Emails;
    }

    public void setEmails(String emails) {
        Emails = emails;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    /* public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }
    */
}

package com.example.education_app;

public class Attendance_List {

    private String name , profile , data, time , p;

    public Attendance_List(){

    }
    public Attendance_List(String name, String profile, String data, String time, String p) {
        this.name = name;
        this.profile = profile;
        this.data = data;
        this.time = time;
        this.p = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }
}

package com.example.education_app;

public class Timetables {
    String modules , day , time , room;

    public Timetables(){

    }

    public Timetables(String modules, String day, String time, String room) {
        this.modules = modules;
        this.day = day;
        this.time = time;
        this.room = room;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}

package com.example.education_app;

public class Schedule {
    String module, day , time;

    public Schedule(){

    }

    public Schedule(String module, String day, String time) {
        this.module = module;
        this.day = day;
        this.time = time;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
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
}

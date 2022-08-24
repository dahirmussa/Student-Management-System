package com.example.education_app;

public class Assignements {
    private String names, date, time;

    public Assignements(){

    }
    public Assignements(String names, String date, String time) {
        this.names = names;
        this.date = date;
        this.time = time;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

package com.example.education_app;

public class Attendance {
    private String names, days, time, present, absent;

    public Attendance(){

    }

    public Attendance(String names, String days, String time, String present, String absent) {
        this.names = names;
        this.days = days;
        this.time = time;
        this.present = present;
        this.absent = absent;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public String getAbsent() {
        return absent;
    }

    public void setAbsent(String absent) {
        this.absent = absent;
    }
}

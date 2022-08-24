package com.example.education_app;

public class workModule
{
    private String name , uri, week,pdf;

    public workModule(){

    }

    public workModule(String name, String uri, String week, String pdf) {
        this.name = name;
        this.uri = uri;
        this.week = week;
        this.pdf = pdf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}

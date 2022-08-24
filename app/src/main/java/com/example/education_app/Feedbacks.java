package com.example.education_app;

public class Feedbacks {
    private String username ,feedback, modules;

    public Feedbacks(){

    }

    public Feedbacks(String username, String feedback, String modules) {
        this.username = username;
        this.feedback = feedback;
        this.modules = modules;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }
}

package com.example.education_app;

public class Grades {
    String modules_names, results, feedback;

    public void Grade(){

    }
    public Grades(String modules_names, String results, String feedback) {
        this.modules_names = modules_names;
        this.results = results;
        this.feedback = feedback;
    }

    public String getModules_names() {
        return modules_names;
    }

    public void setModules_names(String modules_names) {
        this.modules_names = modules_names;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}

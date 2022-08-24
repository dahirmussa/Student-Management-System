package com.example.education_app.chat;

public class ChatList {

    private String name , message , date, time;

    public ChatList(String name, String message, String date, String time) {
        this.name = name;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public ChatList()
    {

    }


    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}

package com.example.education_app;

public class MessageList {

    private String name, email, lastMessage, profilePic, chatKey; //profilePic; // email, profilePic
    private int useenMessage;

    public MessageList(String name, String email, String lastMessage, String profilePic,int useenMessage, String chatKey) {
        this.name = name;
        this.email = email;
        this.lastMessage = lastMessage;
        this.profilePic = profilePic;
        this.useenMessage = useenMessage;
        this.chatKey = chatKey;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public int getUseenMessage() {
        return useenMessage;
    }

    public String getChatKey() {
        return chatKey;
    }


}
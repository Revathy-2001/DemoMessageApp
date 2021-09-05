package com.example.demomessageapp;

public class Message {
    public  String messageContent;
    public  int msg_direction;
    public  int message_id;
    Message(String messageContent,int message_id,int msg_direction){
        this.messageContent = messageContent;
        this.message_id = message_id;
        this.msg_direction = msg_direction;
    }
}

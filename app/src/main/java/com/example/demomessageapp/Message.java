package com.example.demomessageapp;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class Message {
    public  String messageContent;
    public  int msg_direction;
    public  int message_id;
    Message(String messageContent,int message_id,int msg_direction){
        this.messageContent = messageContent;
        this.message_id = message_id;
        this.msg_direction = msg_direction;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageContent='" + messageContent + '\'' +
                ", msg_direction=" + msg_direction +
                ", message_id=" + message_id +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Message message = (Message) obj;
        return msg_direction == message.msg_direction && message_id == message.message_id && messageContent.equals(message.messageContent);
    }
    public static boolean isEqual(Message oldItem, Message newItem){
        return (oldItem.message_id == newItem.message_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageContent, msg_direction, message_id);
    }

    public  static DiffUtil.ItemCallback<Message> itemCallback = new DiffUtil.ItemCallback<Message>() {
        @Override
        public boolean areItemsTheSame(Message oldItem, Message newItem) {
            if(oldItem != null && newItem != null){
              return isEqual(oldItem,newItem);}
            else{  return false;}
        }

        @Override
        public boolean areContentsTheSame(Message oldItem, Message newItem) {
            if(oldItem != null && newItem != null){
                return oldItem.equals(newItem);}
            else{  return false;}
        }
    };
}

package com.example.demomessageapp;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;
@Entity(tableName = "messages")
public class Message {

    @NonNull
    private   String messageContent;

    @NonNull
    private  int user_id;

    @NonNull
    private   int msg_direction;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int message_id;

    Message(){
        Log.e("Constructor","Normal Constructor called");
    }
    public Message(int user_id) {
        this.user_id = user_id;
    }

    public void setMessageContent(@NonNull String messageContent) {
        this.messageContent = messageContent;
    }


    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setMsg_direction(int msg_direction) {
        this.msg_direction = msg_direction;
    }

    //    Message(String messageContent,int message_id,int user_id,int msg_direction){
//        this.messageContent = messageContent;
//        this.msg_direction = msg_direction;
//        this.user_id = user_id;
//    }
    public void setMessage_id(int message_id){
        this.message_id = message_id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public int getMsg_direction() {
        return msg_direction;
    }

    public int getMessage_id(){
        return message_id;
    }
    public int getUser_id(){
        return  user_id;
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
            if(oldItem != null && newItem != null && (oldItem.msg_direction != newItem.msg_direction || oldItem.messageContent != newItem.messageContent) ){
                return false;}
            else{  return true;}
        }
    };
}

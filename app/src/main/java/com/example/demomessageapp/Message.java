package com.example.demomessageapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Message {
    public  String messageContent;
    public  int msg_direction;
    public  int message_id;
    Message(String messageContent,int message_id,int msg_direction){
        this.messageContent = messageContent;
        this.message_id = message_id;
        this.msg_direction = msg_direction;
    }
    public  static DiffUtil.ItemCallback<Message> itemCallback = new DiffUtil.ItemCallback<Message>() {
        @Override
        public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return false;
        }
    };
}

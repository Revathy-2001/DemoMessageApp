package com.example.demomessageapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<Message> messageList = new ArrayList<>();
    LayoutInflater layoutInflater;
    View view;
    static final int LEFT_MESSAGE_TYPE = 1;
    static final int RIGHT_MESSAGE_TYPE = 2;
    MessageAdapter(){

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case  1:
                layoutInflater = LayoutInflater.from(parent.getContext());
                view = layoutInflater.inflate(R.layout.receive_message_layout,parent,false);
                return new LeftViewHolder(view);
            case  2:
                layoutInflater = LayoutInflater.from(parent.getContext());
                view = layoutInflater.inflate(R.layout.send_message_layout,parent,false);
                return new RightViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      if(holder instanceof LeftViewHolder){
         ((LeftViewHolder) holder).textView_message.setText(messageList.get(position).messageContent);
      }
      else if(holder instanceof RightViewHolder){
          ((RightViewHolder) holder).textView_message.setText(messageList.get(position).messageContent);
      }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return  messageList.get(position).msg_direction;
    }
    public  void  submitList(List<Message> messageList){
        this.messageList = messageList;
        notifyDataSetChanged();
    }

    class  LeftViewHolder extends RecyclerView.ViewHolder{
        TextView textView_message;
        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_message = itemView.findViewById(R.id.receive_message_content);
        }
    }
    class  RightViewHolder extends RecyclerView.ViewHolder {
        TextView textView_message;
        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_message = itemView.findViewById(R.id.send_message_content);
        }
    }
}

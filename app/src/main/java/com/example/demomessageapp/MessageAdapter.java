package com.example.demomessageapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends ListAdapter<Message,RecyclerView.ViewHolder> {



    static final int LEFT_MESSAGE_TYPE = 1;
    static final int RIGHT_MESSAGE_TYPE = 2;

    protected MessageAdapter(@NonNull DiffUtil.ItemCallback<Message> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater;
        View view;
        switch (viewType){
            case  LEFT_MESSAGE_TYPE:
                layoutInflater = LayoutInflater.from(parent.getContext());
                view = layoutInflater.inflate(R.layout.receive_message_layout,parent,false);
                return new LeftViewHolder(view);
            case  RIGHT_MESSAGE_TYPE:
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
         ((LeftViewHolder) holder).textView_message.setText(getItem(position).messageContent);
      }
      else{
          ((RightViewHolder) holder).textView_message.setText(getItem(position).messageContent);
      }
    }

    @Override
    public int getItemViewType(int position) {
        return  getItem(position).msg_direction;
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

package com.example.demomessageapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.demomessageapp.databinding.ReceiveMessageLayoutBinding;
import com.example.demomessageapp.databinding.SendMessageLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends ListAdapter<Message,RecyclerView.ViewHolder> {

    static final int LEFT_MESSAGE_TYPE = 1;
    static final int RIGHT_MESSAGE_TYPE = 2;
    Context context;

    protected MessageAdapter(@NonNull DiffUtil.ItemCallback<Message> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater;
        View view;
        context = parent.getContext();
        switch (viewType){
            case  LEFT_MESSAGE_TYPE:
                ReceiveMessageLayoutBinding receiveMessageLayoutBinding = ReceiveMessageLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
                return new LeftViewHolder(receiveMessageLayoutBinding);
            case  RIGHT_MESSAGE_TYPE:
                SendMessageLayoutBinding sendMessageLayoutBinding = SendMessageLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
                return new RightViewHolder(sendMessageLayoutBinding);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      if(holder instanceof LeftViewHolder){
            ((LeftViewHolder) holder).textView_message.setText(getItem(position).getMessageContent());
      }
      else{
              String url = getItem(position).getImage_uri();
              String msg_content = getItem(position).getMessageContent();
              if(msg_content.equals("")) {
                  ((RightViewHolder) holder).textView_message.setVisibility(View.GONE);
                  ((RightViewHolder) holder).imageSend.setVisibility(View.VISIBLE);
              }
              if(url == null){
                  ((RightViewHolder) holder).textView_message.setText(getItem(position).getMessageContent());
              }
              else {
                  Glide.with(context).load(url).placeholder(R.drawable.ic_baseline_account_circle_24).transition(DrawableTransitionOptions.withCrossFade(2000)).into(((RightViewHolder) holder).imageSend);
              }
      }
    }

    @Override
    public int getItemViewType(int position) {
        return  getItem(position).getMsg_direction();
    }

    class  LeftViewHolder extends RecyclerView.ViewHolder{
        TextView textView_message;
        public LeftViewHolder(@NonNull ReceiveMessageLayoutBinding receivebinding) {
            super(receivebinding.getRoot());
            textView_message = receivebinding.receiveMessageContent;
        }
    }

    class  RightViewHolder extends RecyclerView.ViewHolder {
        TextView textView_message;
        ImageView imageSend;
        public RightViewHolder(@NonNull SendMessageLayoutBinding sendBinding) {
            super(sendBinding.getRoot());
            textView_message = sendBinding.sendMessageContent;
            imageSend = sendBinding.imageSend;
        }
    }
}


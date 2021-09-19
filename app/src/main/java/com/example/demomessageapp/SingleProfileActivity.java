package com.example.demomessageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.demomessageapp.databinding.ActivityMainBinding;
import com.example.demomessageapp.databinding.ActivitySingleProfileBinding;

import java.util.ArrayList;
import java.util.List;

public class SingleProfileActivity extends AppCompatActivity {
    ActivitySingleProfileBinding activitySingleProfileBinding;
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    List<Message> messageList;
    ImageButton send_btn,receive_btn;
    EditText editText_message_content;
    MessageViewModel messageViewModel;

    Message message = new Message();

//    MutableLiveData<List<Person>> mutableLiveDataPersons;
//    MainActivityViewModel mainActivityViewModel;
    int i = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySingleProfileBinding = ActivitySingleProfileBinding.inflate(getLayoutInflater());
        View view = activitySingleProfileBinding.getRoot();
        setContentView(view);

//        mainActivityViewModel = new MainActivityViewModel();
//        mutableLiveDataPersons = mainActivityViewModel.mutableLiveData;

        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        Intent intent = getIntent();
        int user_id = intent.getIntExtra("User_id",0);
        message.setUser_id(user_id);
        Log.e("user_id", String.valueOf(user_id));
        messageAdapter = new MessageAdapter(Message.itemCallback);
        messageList = new ArrayList<>();

        recyclerView = activitySingleProfileBinding.chatactivityRecycler;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);

        send_btn = activitySingleProfileBinding.sendBtn;

        receive_btn = activitySingleProfileBinding.receiveBtn;

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_message_content = activitySingleProfileBinding.typeBox;
                String message_content = editText_message_content.getText().toString();
                Log.e("I1", String.valueOf(i));
                i = i++;
                Message message = new Message(i);
//                message.setMessage_id(i);
                message.setMessageContent(message_content);
                message.setMsg_direction(2);
                messageViewModel.insertMessage(message);
            }
        });
        receive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_message_content = activitySingleProfileBinding.typeBox;
                String message_content = editText_message_content.getText().toString();
                Log.e("I2", String.valueOf(i));
                i = i++;
                Message message = new Message(i);
//                message.setMessage_id(i);
                message.setMessageContent(message_content);
                message.setMsg_direction(1);
                messageViewModel.insertMessage(message);
            }
        });

        messageViewModel.getAllMessagesFromDB().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messageList) {
              messageAdapter.submitList(messageList);
            }
        });
    }
}

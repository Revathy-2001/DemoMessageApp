package com.example.demomessageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class SingleProfileActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    List<Message> messageList;
    ImageButton send_btn,receive_btn;
    EditText editText_message_content;

    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_profile);
        Intent intent = getIntent();
        intent.getStringExtra("Firstname");
        messageAdapter = new MessageAdapter(Message.itemCallback);
        messageList = new ArrayList<>();
        recyclerView = findViewById(R.id.chatactivity_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);

        send_btn = (ImageButton) findViewById(R.id.send_btn);
        receive_btn = (ImageButton) findViewById(R.id.receive_btn);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_message_content = findViewById(R.id.type_box);
                String message_content = editText_message_content.getText().toString();
                Message message = new Message(message_content,i++,2);
                messageList.add(message);
                messageAdapter.submitList(messageList);
            }
        });
        receive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_message_content = findViewById(R.id.type_box);
                String message_content = editText_message_content.getText().toString();
                Message message = new Message(message_content,i++,1);
                messageList.add(message);
                messageAdapter.submitList(messageList);
            }
        });
    }
}
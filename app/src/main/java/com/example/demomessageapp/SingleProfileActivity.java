package com.example.demomessageapp;

import androidx.annotation.Nullable;
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
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

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
    MessageDao messageDao;
    String send_to_name;
    int user_id;
    private  static final int GALLERY_REQUEST_CODE = 123;
    int i = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySingleProfileBinding = ActivitySingleProfileBinding.inflate(getLayoutInflater());
        View view = activitySingleProfileBinding.getRoot();
        setContentView(view);

        ImageView galleryIcon = activitySingleProfileBinding.galleryIcon;
        galleryIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Pick an images"),GALLERY_REQUEST_CODE);
            }
        });


        Intent intent = getIntent();
        user_id = intent.getIntExtra("User_id",0);
        String name = intent.getStringExtra("Name");
        send_to_name = name;
        setTitle(name);

        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        messageViewModel.setUserIdToFetch(user_id);
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
                if(message_content.isEmpty()){
                    editText_message_content.setError("Message can't be sent Empty!!!");
                    editText_message_content.requestFocus();
                }
                else {
                    Log.e("I1", String.valueOf(i));
                    i = i++;
                    Message message = new Message(i,user_id,message_content,2,null);
                    messageViewModel.insertMessage(message);
                    editText_message_content.setText("");
                }
            }
        });
        receive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_message_content = activitySingleProfileBinding.typeBox;
                String message_content = editText_message_content.getText().toString();
                if(message_content.isEmpty()){
                    editText_message_content.setError("Message can't be sent Empty!!!");
                    editText_message_content.requestFocus();
                }
                else {
                    Log.e("I2", String.valueOf(i));
                    i = i++;
                    Message message = new Message(i,user_id,message_content,1,null);
                    messageViewModel.insertMessage(message);
//                    recyclerView.scrollToPosition(messageList.size() - 1);
                    editText_message_content.setText("");
                }
            }
        });

        messageViewModel.getAllMessagesFromDB().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messageList) {
              messageAdapter.submitList(messageList);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri image_uri = data.getData();
            Intent intent = new Intent(this, SendPhotoActivity.class);
            intent.putExtra("image-uri", image_uri.toString());
            intent.putExtra("ReceiverName",send_to_name);
            intent.putExtra("User_id",user_id);
            intent.putExtra("I",i);
            startActivity(intent);
        }
    }
}

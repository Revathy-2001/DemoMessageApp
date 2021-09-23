package com.example.demomessageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.demomessageapp.databinding.ActivityPhotosBinding;
import com.example.demomessageapp.databinding.ActivitySendPhotoBinding;

public class SendPhotoActivity extends AppCompatActivity {
    ActivitySendPhotoBinding activitySendPhotoBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySendPhotoBinding = ActivitySendPhotoBinding.inflate(getLayoutInflater());
        View view = activitySendPhotoBinding.getRoot();

        setContentView(view);
        ImageView image_selected = activitySendPhotoBinding.singleImageSend;
        Intent intent = getIntent();
        int user_id = intent.getIntExtra("User_id",0);
        int i = intent.getIntExtra("I",0);
        setTitle("Send to " + intent.getStringExtra("ReceiverName"));
        String image_url = intent.getStringExtra("image-uri");
        Uri imageUri = Uri.parse(intent.getStringExtra("image-uri"));
        Glide.with(this).load(imageUri).placeholder(R.drawable.image_icon).transition(DrawableTransitionOptions.withCrossFade(1000)).into(image_selected);
        ImageButton send_btn = activitySendPhotoBinding.sendPhotoBtn;
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message(i,user_id,"",2,null);
                message.setImage_uri(image_url);
                MessageViewModel messageViewModel =  new ViewModelProvider(SendPhotoActivity.this).get(MessageViewModel.class);
                messageViewModel.insertMessage(message);
                Log.e("URL",message.getImage_uri());
                Intent intent = new Intent(SendPhotoActivity.this,SingleProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
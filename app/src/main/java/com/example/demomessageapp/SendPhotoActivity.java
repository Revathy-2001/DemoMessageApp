package com.example.demomessageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
        setTitle("Send to " + intent.getStringExtra("ReceiverName"));
        Uri imageUri = Uri.parse(intent.getStringExtra("image-uri"));
        Glide.with(this).load(imageUri).placeholder(R.drawable.image_icon).transition(DrawableTransitionOptions.withCrossFade(1000)).into(image_selected);
    }
}
package com.example.demomessageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.example.demomessageapp.databinding.ActivityPhotosBinding;
import com.example.demomessageapp.databinding.ActivitySingleProfileBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PhotosActivity extends AppCompatActivity {
    ActivityPhotosBinding activityPhotosBinding;
    ArrayList<MediaStore.Audio.Media> imageList;

    List<String> listOfAllImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPhotosBinding = ActivityPhotosBinding.inflate(getLayoutInflater());
        View view = activityPhotosBinding.getRoot();
        setContentView(view);

        final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };
        final String orderBy = MediaStore.Images.Media._ID;
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI, columns, null,
                null, orderBy);
        int count = cursor.getCount();

        List<String> arrPath = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            arrPath.add(cursor.getString(dataColumnIndex));
        }
        cursor.close();

        PhotosAdapter photosAdapter = new PhotosAdapter(arrPath);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = activityPhotosBinding.photosRecyclerView;
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(photosAdapter);

    }
}
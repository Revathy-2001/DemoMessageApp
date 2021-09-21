package com.example.demomessageapp;

import android.content.Context;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demomessageapp.databinding.RowItemBinding;
import com.example.demomessageapp.databinding.SingleImageItemBinding;

import java.util.ArrayList;
import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {
    Context context;
    List<String> image_list;
    PhotosAdapter(List<String> listImages){
        this.image_list = listImages;
    }
    @NonNull
    @Override
    public PhotosAdapter.PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleImageItemBinding singleImageItemBinding = SingleImageItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        Log.e("Parent", String.valueOf(parent));
        context = parent.getContext();
        return new PhotosViewHolder(singleImageItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosAdapter.PhotosViewHolder holder, int position) {

        Glide.with(context)
                .load(image_list.get(position))
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .error(R.drawable.ic_baseline_account_circle_24)  // any image in case of error
                .into(holder.image_single);
    }

    @Override
    public int getItemCount() {
        return image_list.size();
    }

    public class PhotosViewHolder extends RecyclerView.ViewHolder {
        ImageView image_single;
        public PhotosViewHolder(@NonNull SingleImageItemBinding binding) {
            super(binding.getRoot());
            image_single = binding.singleImage;
        }

    }
}

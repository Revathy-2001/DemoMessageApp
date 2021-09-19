package com.example.demomessageapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.demomessageapp.databinding.RowItemBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<Person> personList = new ArrayList<>();
    PersonClickListener personClickListener;
    public RecyclerAdapter(PersonClickListener personClickListener){
        this.personClickListener = personClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RowItemBinding rowItemBinding = RowItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
            Log.e("Parent", String.valueOf(parent));
            context = parent.getContext();
            return new ViewHolder(rowItemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         ViewHolder h = (ViewHolder)holder;
         String url = personList.get(position).getAvatar();
         h.textView_firstName.setText(personList.get(position).getFirstName());
         h.textView_lastName.setText(personList.get(position).getLastName());
         Glide.with(context).load(url).placeholder(R.drawable.ic_baseline_account_circle_24).transition(DrawableTransitionOptions.withCrossFade(2000)).into(h.imageView);
         Log.e("PersonList",String.valueOf(personList.get(position).getId()));
    }

    @Override
    public int getItemCount() {
       return personList.size();
    }

    public void submitList(List<Person> person){
        this.personList = person;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView_firstName,textView_lastName,textView_time;
        public ViewHolder(@NonNull RowItemBinding binding) {
            super(binding.getRoot());

            imageView = binding.profile;
            textView_firstName = binding.firstName;
            textView_lastName = binding.lastName;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  personClickListener.invokeMethod(personList.get(getAdapterPosition()).getId());
                }
            });
        }

    }
}
interface PersonClickListener{
    public  void invokeMethod(int id);
}
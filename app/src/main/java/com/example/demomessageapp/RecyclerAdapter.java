package com.example.demomessageapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demomessageapp.databinding.RowItemBinding;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    List<Person> personList;
    PersonClickListener personClickListener;
    public RecyclerAdapter(PersonClickListener personClickListener){
        this.personClickListener = personClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.row_item,parent,false);

        RowItemBinding rowItemBinding = RowItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        Log.e("Parent", String.valueOf(parent));
        return new ViewHolder(rowItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.textView_firstName.setText(personList.get(position).firstName);
         holder.textView_lastName.setText(personList.get(position).lastName);
         holder.textView_time.setText(personList.get(position).time);
    }

    @Override
    public int getItemCount() {
       return personList.size();

    }

    public void submitList(List<Person> person){
        this.personList = person;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView_firstName,textView_lastName,textView_time;
        public ViewHolder(@NonNull RowItemBinding binding) {
            super(binding.getRoot());
           // imageView = itemView.findViewById(R.id.profile);
           // textView_firstName = itemView.findViewById(R.id.first_name);
           // textView_lastName = itemView.findViewById(R.id.last_name);
           // textView_time = itemView.findViewById(R.id.time);

            imageView = binding.profile;
            textView_firstName = binding.firstName;
            textView_lastName = binding.lastName;
            textView_time = binding.time;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  personClickListener.invokeMethod(personList.get(getAdapterPosition()).firstName);
                }
            });
        }

    }
}
interface PersonClickListener{
    public  void invokeMethod(String firstName);
}
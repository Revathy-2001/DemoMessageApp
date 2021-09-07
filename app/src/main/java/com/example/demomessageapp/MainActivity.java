package com.example.demomessageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PersonClickListener{

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<Person> person_details;
//    Calendar calendar = Calendar.getInstance();
//    SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
//    String currentTime = "Current Time : " + mdformat.format(calendar.getTime());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        person_details = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(this);
        recyclerAdapter.submitList(person_details);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        person_details.add(new Person("Aadhya","Krishna",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Ahana","Moorthy",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Aaleyah","Vineet",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Aarya","Kannan",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Advika","Ameet",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Akshara","Moorthi",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Basheera","Begum",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Bhavana","Ravikumar",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Bhakya","Lakshmi",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Brindha","Krishnan",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Charulatha","Ravinarayanan",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Charles","Krish",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("David","Ragu",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Dharshitha","Varnan",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Devika","Kannan",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Felina","Raj",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Manisha","Kravel",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Nivetha","Antony",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Swetha","hariharan",randomTime(1,24)+"."+ randomTime(1,60)));
        person_details.add(new Person("Zaara","Vinneet",randomTime(1,24)+"."+ randomTime(1,60)));

    }
    public String randomTime(int min,int max){
        String currentTime = (int)(Math.random()*(max-min+1)+min)+"";
        if(currentTime.length() == 1)
            currentTime = "0"+currentTime;
        return currentTime;
    }

    @Override
    public void invokeMethod(String firstName) {
        Intent intent = new Intent(this,SingleProfileActivity.class);
        startActivity(intent);
    }
}
class  Person{
  public String firstName;
  public String lastName;
  public String time;

  Person(String firstName,String lastName,String time){
      this.firstName = firstName;
      this.lastName = lastName;
      this.time = time;
  }
}
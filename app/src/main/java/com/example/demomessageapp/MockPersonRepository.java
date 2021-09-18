package com.example.demomessageapp;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class MockPersonRepository implements  PersonRepository{
    MutableLiveData<List<Person>> personLiveData = new MutableLiveData<>();
    @Override
    public MutableLiveData<List<Person>> getLiveData() {
        return personLiveData;
    }

    @Override
    public void loadPersons() {
      List<Person> personList = new ArrayList<>();
      Person p = new Person();
      p.setPerson(0,"Smith","Rausel","smith@gmail.com","url");
      personList.add(p);
      personLiveData.postValue(personList);
     }
}

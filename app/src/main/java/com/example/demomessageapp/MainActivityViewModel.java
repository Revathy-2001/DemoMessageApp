package com.example.demomessageapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    MutableLiveData<List<Person>> personsLiveData = new MutableLiveData<List<Person>>();
    public MainActivityViewModel(){
       loadPersons();
    }
    private void loadPersons(){
        List<Person> person_details = new ArrayList<>();
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
        personsLiveData.setValue(person_details);
    }
    public String randomTime(int min,int max){
        String currentTime = (int)(Math.random()*(max-min+1)+min)+"";
        if(currentTime.length() == 1)
            currentTime = "0"+currentTime;
        return currentTime;
    }
}

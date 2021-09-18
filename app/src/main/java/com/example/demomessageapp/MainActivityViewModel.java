package com.example.demomessageapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demomessageapp.databinding.RowItemBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityViewModel extends ViewModel {
      MutableLiveData<List<Person>> mutableLiveData;


      PersonRepository dataPersonRepository = new DataPersonRepository();
//      MockPersonRepository mockPersonRepository = new MockPersonRepository();
      public MainActivityViewModel(){
          dataPersonRepository.loadPersons();
//          mockPersonRepository.loadPersons();
          mutableLiveData = dataPersonRepository.getLiveData();
//          mutableLiveData = mockPersonRepository.getLiveData();
    }
}

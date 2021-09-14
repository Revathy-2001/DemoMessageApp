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
    public String result;
    MutableLiveData<List<Person>> personsLiveData = new MutableLiveData<List<Person>>();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    ReqresApi reqresApi = retrofit.create(ReqresApi.class);
    Call<Persons> call = reqresApi.getPersons();
    public MainActivityViewModel(){
       loadPersons();
    }
    private void loadPersons(){
        call.enqueue(new Callback<Persons>() {

            @Override
            public void onResponse(Call<Persons> call, Response<Persons> response) {
               if(!response.isSuccessful()){
                   result = "Code: " + response.code();
                   return;
               }
               personsLiveData.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<Persons> call, Throwable t) {
               result = t.getMessage();
            }

        });
    }

}

package com.example.demomessageapp;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataPersonRepository implements PersonRepository {

    MutableLiveData<List<Person>> personsLiveData = new MutableLiveData<List<Person>>();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    ReqresApi reqresApi = retrofit.create(ReqresApi.class);
    Call<Persons> call = reqresApi.getPersons();


    public void loadPersons() {
        call.enqueue(new Callback<Persons>() {

            @Override
            public void onResponse(Call<Persons> call, Response<Persons> response) {
                if (!response.isSuccessful()) {
                    String result = "Code: " + response.code();
                    return;
                }
                personsLiveData.postValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<Persons> call, Throwable t) {
                String result = t.getMessage();
            }

        });

    }

    @Override
    public MutableLiveData<List<Person>> getLiveData() {
        return personsLiveData;
    }


}

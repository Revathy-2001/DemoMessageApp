package com.example.demomessageapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ReqresApi {
    @GET("api/users") //http get request. The string inside the parameter is the API name.
    Call<Persons> getPersons();
}


//Type of a call should be list.
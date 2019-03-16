package com.project.mausam.common;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SetupRetrofit {

    String BASE_URL = "https://samples.openweathermap.org/data/2.5/";

    public Retrofit getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}

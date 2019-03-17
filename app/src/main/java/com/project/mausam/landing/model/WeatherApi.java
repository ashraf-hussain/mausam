package com.project.mausam.landing.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherApi {


    @GET("forecast?zip=30033&APPID=f7ded71186bc11866bcb3258bfa5b418")
    Call<ResponseBody> getLandingData();
}

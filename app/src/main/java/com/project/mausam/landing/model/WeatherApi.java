package com.project.mausam.landing.model;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherApi {

    @GET("forecast?zip=30303&appid=b6907d289e10d714a6e88b30761fae22")
   // Call<WeatherResponse<WeatherModel>> getLandingData();
    Call<ResponseBody> getLandingData();
}

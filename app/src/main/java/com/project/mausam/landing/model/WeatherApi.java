package com.project.mausam.landing.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {


    @GET("forecast")
    Call<ResponseBody> getLandingData(@Query("zip") String zipCode,
                                      @Query("APPID") String apikey,
                                      @Query("units") String unit);
}

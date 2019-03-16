package com.project.mausam.landing.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.project.mausam.common.SetupRetrofit;
import com.project.mausam.landing.model.WeatherApi;
import com.project.mausam.landing.model.WeatherModel;
import com.project.mausam.landing.model.WeatherParser;
import com.project.mausam.landing.model.WeatherResponse;
import com.project.mausam.landing.view.LandingView;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LandingImp implements LandingPresenter {

    LandingView view;

    public LandingImp(LandingView view) {
        this.view = view;
    }

    @Override
    public void loadWeatherData() {

        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofit();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        weatherApi.getLandingData().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("onResponse: ", String.valueOf(response.code()));
                try {
                    WeatherParser weatherParser = new WeatherParser(response.body().string());
                    Log.d("onResponseUrl: ", String.valueOf(response.raw().request().url()));



                    weatherParser.weatherDataParse();

                    view.showLandingData(weatherParser.getWeatherModelArrayList());

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}

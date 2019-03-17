package com.project.mausam.landing.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.project.mausam.common.AppConstant;
import com.project.mausam.common.SetupRetrofit;
import com.project.mausam.landing.model.WeatherApi;
import com.project.mausam.landing.model.WeatherParser;
import com.project.mausam.landing.view.LandingView;
import com.project.mausam.start.SettingsActivity;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class LandingImp implements LandingPresenter {

    LandingView view;
    SharedPreferences sharedPreferences;
    Context context;

    public LandingImp(LandingView view, Context context) {
        this.view = view;
        this.context = context;
    }


    @Override
    public void loadWeatherData() {
        sharedPreferences = context.getSharedPreferences(SettingsActivity.MY_DATA, MODE_PRIVATE);

        String zipCodeDataCheck = sharedPreferences.getString(SettingsActivity.zip, "");
        String unitCheck = sharedPreferences.getString(SettingsActivity.unit, "imperial");
        Log.d( "unit", unitCheck);

        SetupRetrofit setupRetrofit = new SetupRetrofit();
        Retrofit retrofit = setupRetrofit.getRetrofit();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);


        weatherApi.getLandingData(zipCodeDataCheck, AppConstant.API_KEY,unitCheck).enqueue(new Callback<ResponseBody>() {

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

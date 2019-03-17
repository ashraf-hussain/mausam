package com.project.mausam.landing.model;

import android.content.ContentUris;
import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherParser {

    String result;
    WeatherModel weatherModel;
    ArrayList<WeatherModel> weatherModelArrayList;

    public ArrayList<WeatherModel> getWeatherModelArrayList() {
        return weatherModelArrayList;
    }

    public WeatherParser(String result) {
        this.result = result;
        weatherModelArrayList = new ArrayList<>();
    }


    public void weatherDataParse() throws JSONException {
        JSONObject jsonObject = new JSONObject(result);

        JSONArray list = jsonObject.getJSONArray("list");
        for (int i = 0; i < list.length(); i++) {
            weatherModel = new WeatherModel();

            JSONObject data = list.getJSONObject(i);

            weatherModel.setDtTxt(data.getString(("dt_txt")));


            JSONObject main = data.getJSONObject("main");

            weatherModel.setTempKf(main.getInt("humidity"));


            JSONArray weather = data.getJSONArray("weather");
            for (int imain = 0; imain < weather.length(); imain++) {

                JSONObject mainobj = weather.getJSONObject(imain);

                weatherModel.setMain(mainobj.getString("description"));

            }

            JSONObject country = jsonObject.getJSONObject("city");

            weatherModel.setCountry(country.getString("country"));
            weatherModel.setCityName(country.getString("name"));
            weatherModelArrayList.add(weatherModel);

        }


    }
}

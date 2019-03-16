package com.project.mausam.landing.model;


import java.util.List;

public class WeatherResponse<T> {


    public List<T> getWeatherData() {
        return WeatherData;
    }

    private List<T> WeatherData;
}

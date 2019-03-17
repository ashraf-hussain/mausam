package com.project.mausam.landing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherModel {
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;
    @SerializedName("temp_kf")
    @Expose
    private double tempKf;

    @SerializedName("main")
    @Expose
    private String main;

    @SerializedName("name")
    @Expose
    private String cityName;

    @SerializedName("country")
    @Expose
    private String country;

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public double getTempKf() {
        return tempKf;
    }

    public void setTempKf(double tempKf) {
        this.tempKf = tempKf;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}

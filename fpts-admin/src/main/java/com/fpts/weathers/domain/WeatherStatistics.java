package com.fpts.weathers.domain;

public class WeatherStatistics {
    private String city;
    private int cityCnt;

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityCnt(int cityCnt) {
        this.cityCnt = cityCnt;
    }

    public String getCity() {
        return city;
    }

    public int getCityCnt() {
        return cityCnt;
    }
}

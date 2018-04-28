package com.example.hsxfd.cyzretrofit.model;

import com.example.hsxfd.cyzretrofit.presenter.Model;

import java.util.ArrayList;

public class WeatherModel implements Model {
    private String currentCity;
    private String pm25;
    private ArrayList<Index> index;
    private ArrayList<WeatherData> weather_data;

    public class Index{
        private String title;
        private String zs;
        private String tipt;
        private String des;

        @Override
        public String toString() {
            return "Index{" +
                    "title='" + title + '\'' +
                    ", zs='" + zs + '\'' +
                    ", tipt='" + tipt + '\'' +
                    ", des='" + des + '\'' +
                    '}';
        }
    }

    public class WeatherData{
        private String date;
        private String dayPictureUrl;
        private String nightPictureUrl;
        private String weather;
        private String wind;
        private String temperature;

        @Override
        public String toString() {
            return "WeatherData{" +
                    "date='" + date + '\'' +
                    ", dayPictureUrl='" + dayPictureUrl + '\'' +
                    ", nightPictureUrl='" + nightPictureUrl + '\'' +
                    ", weather='" + weather + '\'' +
                    ", wind='" + wind + '\'' +
                    ", temperature='" + temperature + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "currentCity='" + currentCity + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", index=" + index +
                ", weather_data=" + weather_data +
                '}';
    }
}

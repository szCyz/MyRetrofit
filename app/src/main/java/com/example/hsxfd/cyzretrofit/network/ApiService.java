package com.example.hsxfd.cyzretrofit.network;


import com.example.hsxfd.cyzretrofit.model.WeatherModel;

import java.util.HashMap;
import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("telematics/v3/weather")
    Observable<WrapperRspEntity<List<WeatherModel>>> searchWeather(@QueryMap HashMap<String, Object> params);
}

package com.example.currentweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    String apiId   =   "bb4fe7427fed43c0e54edb0f115ed6ad";
    String temperatureType = "metric";
    @GET("weather")
    Call<GetWeatherResponse> getWeather(
            @Query("q") String nameCity,
            @Query("units") String temperatureType,
            @Query("appid") String apiKey
    );
}

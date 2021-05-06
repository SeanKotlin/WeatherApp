package com.sklinn.weathermap.Remote

import com.sklinn.weathermap.Model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    fun getWeatherData(
        @Query("q") city: String,
        @Query("appid") apikey: String,
    ):Call<Weather>
}
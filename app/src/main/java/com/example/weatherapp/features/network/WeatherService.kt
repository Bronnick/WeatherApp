package com.example.weatherapp.features.network

import com.example.example.WeatherApiResponse
import com.example.weatherapp.features.data.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherService {

    @GET("current.json")
    suspend fun getWeatherInfo(
        @Header("key") apiKey: String,
        @Query("q") cityName: String
    ): WeatherApiResponse
}
package com.example.weatherapp.features.network

import com.example.example.WeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast.json")
    suspend fun getWeatherInfo(
        @Header("key") apiKey: String,
        @Query("q") cityName: String,
        @Query("days") numberOfDays: Int = 1,
    ): WeatherApiResponse

    @GET("search.json")
    suspend fun getAutocompleteInfo(
        @Header("key") apiKey: String,
        @Query("q") query: String
    ): List<SearchAutocompleteResponse>
}
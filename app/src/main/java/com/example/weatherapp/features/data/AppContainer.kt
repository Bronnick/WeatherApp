package com.example.weatherapp.features.data

import com.example.weatherapp.features.network.WeatherService
import com.example.weatherapp.features.repository.NetworkWeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appContainer by lazy{
    AppContainer()
}

class AppContainer {
    private val baseUrl = "https://api.weatherapi.com/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService by lazy{
        retrofit.create<WeatherService>()
    }

    val networkWeatherRepository by lazy{
        NetworkWeatherRepository(retrofitService)
    }

}
package com.example.weatherapp.features.repository

import com.example.example.Current
import com.example.example.WeatherApiResponse
import com.example.weatherapp.features.data.WeatherInfo
import com.example.weatherapp.features.network.WeatherService

interface WeatherRepository{
    suspend fun getWeatherInfo(cityName: String): WeatherInfo
}

class NetworkWeatherRepository(
    private val weatherService: WeatherService
) : WeatherRepository {

    private val apiKey = "e3795f87e4574b59a40145056233107"

    override suspend fun getWeatherInfo(
        cityName: String
    ): WeatherInfo {
        return weatherService.getWeatherInfo(apiKey, cityName).run {
            WeatherInfo(
                city = location?.name,
                country = location?.country,
                temperatureCelsius = current?.tempC,
                temperatureFahrenheit = current?.tempF,
                feelsLikeCelsius = current?.feelslikeC,
                feelsLikeFahrenheit = current?.feelslikeF,
                condition = current?.condition,
                localDate = location?.localtime,
                hourForecastList = forecast?.forecastday?.get(0)?.hour ?: emptyList()
            )
        }
    }
}
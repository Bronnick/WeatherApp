package com.example.weatherapp.features.repository

import com.example.example.Current
import com.example.example.WeatherApiResponse
import com.example.weatherapp.features.data.AutocompleteInfo
import com.example.weatherapp.features.data.WeatherInfo
import com.example.weatherapp.features.network.WeatherService

interface WeatherRepository{
    suspend fun getWeatherInfo(
        cityName: String,
        numberOfDays: Int
    ): WeatherInfo

    suspend fun getSearchInfo(
        query: String
    ): List<AutocompleteInfo>
}

class NetworkWeatherRepository(
    private val weatherService: WeatherService
) : WeatherRepository {

    private val apiKey = "e3795f87e4574b59a40145056233107"

    override suspend fun getWeatherInfo(
        cityName: String,
        numberOfDays: Int
    ): WeatherInfo {
        return weatherService.getWeatherInfo(apiKey, cityName, numberOfDays).run {
            WeatherInfo(
                city = location?.name,
                country = location?.country,
                temperatureCelsius = current?.tempC,
                temperatureFahrenheit = current?.tempF,
                feelsLikeCelsius = current?.feelslikeC,
                feelsLikeFahrenheit = current?.feelslikeF,
                condition = current?.condition,
                localDate = location?.localtime,
                hourForecastList = forecast?.forecastday?.get(0)?.hour ?: emptyList(),
                weekListForecast = forecast?.forecastday ?: emptyList()
            )
        }
    }

    override suspend fun getSearchInfo(
        query: String
    ): List<AutocompleteInfo> {
        return weatherService.getAutocompleteInfo(apiKey, query).map{item ->
            AutocompleteInfo(
                name = item.name,
                region = item.region,
                country = item.country
            )
        }
    }
}
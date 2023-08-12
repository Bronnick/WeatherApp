package com.example.weatherapp.features.data.classes

import com.example.weatherapp.features.network.classes.Condition
import com.example.weatherapp.features.network.classes.Forecastday
import com.example.weatherapp.features.network.classes.Hour

data class WeatherInfo(

    val city: String?,
    val country: String?,

    val temperatureCelsius: Double?,
    val temperatureFahrenheit: Double?,
    val feelsLikeCelsius: Double?,
    val feelsLikeFahrenheit: Double?,

    val condition: Condition?,

    val localDate: String?,

    val hourForecastList: List<Hour>,
    val weekListForecast: List<Forecastday>

)
package com.example.weatherapp.features.data

import com.example.example.Condition

data class WeatherInfo(

    val city: String?,
    val country: String?,

    val temperatureCelsius: Double?,
    val temperatureFahrenheit: Double?,
    val feelsLikeCelsius: Double?,
    val feelsLikeFahrenheit: Double?,

    val condition: Condition?,

)
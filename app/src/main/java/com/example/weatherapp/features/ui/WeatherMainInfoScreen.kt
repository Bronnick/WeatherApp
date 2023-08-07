package com.example.weatherapp.features.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import com.example.weatherapp.features.data.WeatherInfo
import com.example.weatherapp.features.data.TemperatureState

@Composable
fun WeatherMainInfoScreen(
    weatherInfo: WeatherInfo,
    temperatureState: TemperatureState
){

    Column {
        WeatherCurrentInfo(
            weatherInfo = weatherInfo,
            temperatureState = temperatureState
        )

        WeatherForecastInfo(
            weatherInfo = weatherInfo
        )

        WeatherWeekForecastInfo(
            weatherInfo = weatherInfo,
            temperatureState = temperatureState
        )
    }

}
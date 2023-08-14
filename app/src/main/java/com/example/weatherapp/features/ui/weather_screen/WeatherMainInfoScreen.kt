package com.example.weatherapp.features.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weatherapp.features.data.classes.WeatherInfo
import com.example.weatherapp.features.data.TemperatureState

@Composable
fun WeatherMainInfoScreen(
    weatherInfo: WeatherInfo,
    temperatureState: TemperatureState
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        WeatherCurrentInfo(
            weatherInfo = weatherInfo,
            temperatureState = temperatureState
        )

        WeatherForecastInfo(
            weatherInfo = weatherInfo,
            temperatureState = temperatureState
        )

        WeatherWeekForecastInfo(
            weatherInfo = weatherInfo,
            temperatureState = temperatureState
        )
    }

}
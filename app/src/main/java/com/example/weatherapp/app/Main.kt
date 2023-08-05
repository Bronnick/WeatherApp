package com.example.weatherapp.app

import com.example.weatherapp.features.data.AppContainer
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope<Unit>{
    val container = AppContainer()
    val repository = container.networkWeatherRepository

    launch {
        println(repository.getWeatherInfo("Kiev",1))
    }
}
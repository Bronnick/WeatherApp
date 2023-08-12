package com.example.weatherapp.features.network.classes

import com.google.gson.annotations.SerializedName


data class Forecast (

  @SerializedName("forecastday" ) var forecastday : List<Forecastday> = listOf()

)
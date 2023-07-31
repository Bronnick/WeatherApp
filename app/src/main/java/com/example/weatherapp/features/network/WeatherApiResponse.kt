package com.example.example

import com.google.gson.annotations.SerializedName


data class WeatherApiResponse (

  @SerializedName("location" ) var location : Location? = Location(),
  @SerializedName("current"  ) var current  : Current?  = Current()

)
package com.example.weatherapp.features.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.features.data.classes.WeatherInfo

@Composable
fun WeatherForecastInfo(
    weatherInfo: WeatherInfo
){
    val forecastList = weatherInfo.hourForecastList
    Box(
        modifier = Modifier
           .padding(all = 4.dp)
           .fillMaxWidth()
           .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(ScrollState(0))
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for(item in forecastList){
                Column{
                    Text(
                       text = item.let{
                           it.time?.substringAfter(" ")
                       }.toString(),
                    )
                    AsyncImage(
                        modifier = Modifier
                            .size(50.dp),
                        model = "https:${item.condition?.icon}",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}
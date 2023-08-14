package com.example.weatherapp.features.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.features.data.classes.WeatherInfo
import com.example.weatherapp.R
import com.example.weatherapp.features.data.TemperatureState

@Composable
fun WeatherForecastInfo(
    weatherInfo: WeatherInfo,
    temperatureState: TemperatureState
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
                .horizontalScroll(rememberScrollState())
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for(item in forecastList){

                val temperatureValue =
                    if(temperatureState == TemperatureState.CELSIUS) item.tempC
                    else item.tempF

                val temperatureString =
                    if(temperatureState == TemperatureState.CELSIUS) "C"
                    else "F"

                Column(
                    modifier = Modifier
                        .padding(end = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                       text = item.let{
                           it.time?.substringAfter(" ")
                       }.toString(),
                    )
                    AsyncImage(
                        modifier = Modifier
                            .size(50.dp),
                        model = stringResource(
                            id = R.string.https,
                            item.condition?.icon.toString()
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = stringResource(
                            id = R.string.temperature_info,
                            temperatureValue.toString(),
                            temperatureString
                        )
                    )
                }
            }
        }
    }
}
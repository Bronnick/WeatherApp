package com.example.weatherapp.features.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.features.data.WeatherInfo


@Composable
fun WeatherScreen(
    weatherInfo: WeatherInfo
){
    Box(
        modifier = Modifier
            .padding(all = 4.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ){
        Row{
            Text(
                text = "${weatherInfo.temperatureCelsius}°",
                fontSize = 30.sp,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}
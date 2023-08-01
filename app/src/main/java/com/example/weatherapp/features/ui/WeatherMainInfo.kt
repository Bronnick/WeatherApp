package com.example.weatherapp.features.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.features.data.WeatherInfo
import com.example.weatherapp.R
import com.example.weatherapp.app.theme.Shapes

@Composable
fun WeatherMainInfo(
    weatherInfo: WeatherInfo
){
    Box(
        modifier = Modifier
            .padding(all = 4.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background),
        contentAlignment = Alignment.CenterEnd
    ) {
        Column (modifier = Modifier
            .padding(all = 24.dp)
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "${weatherInfo.temperatureCelsius}°",
                    fontSize = 30.sp,
                    color = MaterialTheme.colors.onBackground
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(
                        modifier = Modifier
                            .size(50.dp),
                        model = "https:${weatherInfo.condition?.icon}",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = weatherInfo.condition?.text ?: "",
                        textAlign = TextAlign.Center
                    )
                }
            }
            
            Text(
                text = stringResource(
                    id = R.string.feels_like_celsius,
                    weatherInfo.feelsLikeCelsius.toString()
                ),
                modifier = Modifier
                    .padding(top=8.dp)
            )
        }
    }
}
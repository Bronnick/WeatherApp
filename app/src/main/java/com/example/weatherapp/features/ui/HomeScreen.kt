package com.example.weatherapp.features.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.features.data.WeatherInfo
import com.example.weatherapp.features.data.WeatherUiState

@Composable
fun HomeScreen(
    weatherUiState: WeatherUiState,
    modifier: Modifier
){
    val brightness = -50f
    val colorMatrix = ColorMatrix(floatArrayOf(
        1f, 0f, 0f, 0f, brightness,
        0f, 1f, 0f, 0f, brightness,
        0f, 0f, 1f, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
        )
    )

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.sunset_image),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        colorFilter = ColorFilter.colorMatrix(colorMatrix)
    )

    when(weatherUiState) {
        is WeatherUiState.Success -> WeatherMainInfo(weatherUiState.weatherInfo)
        is WeatherUiState.Error -> Text(text = "Error") //TODO
        is WeatherUiState.Loading -> Text(text = "LOading") //TODO
    }

}
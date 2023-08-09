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
import com.example.weatherapp.features.data.BackgroundImageState
import com.example.weatherapp.features.data.TemperatureState
import com.example.weatherapp.features.data.WeatherInfo
import com.example.weatherapp.features.data.WeatherUiState

@Composable
fun HomeScreen(
    weatherUiState: WeatherUiState,
    temperatureState: TemperatureState,
    backgroundImageState: BackgroundImageState,
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colors.onPrimary
            )
    ) {

        if (backgroundImageState == BackgroundImageState.ENABLED) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.sunset_image),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                colorFilter = ColorFilter.colorMatrix(colorMatrix)
            )
        }

        when (weatherUiState) {
            is WeatherUiState.Success -> WeatherMainInfoScreen(
                weatherInfo = weatherUiState.weatherInfo,
                temperatureState = temperatureState
            )
            is WeatherUiState.Error -> ErrorScreen(
                errorIconId = weatherUiState.errorIconId,
                messageId = weatherUiState.messageId
            )
            is WeatherUiState.Loading -> LoadingScreen()
        }
    }

}
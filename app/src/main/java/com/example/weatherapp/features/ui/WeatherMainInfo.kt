package com.example.weatherapp.features.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.features.data.WeatherInfo
import com.example.weatherapp.R
import com.example.weatherapp.app.theme.Shapes
import com.example.weatherapp.features.data.TemperatureState

@Composable
fun WeatherMainInfo(
    weatherInfo: WeatherInfo,
    temperatureState: TemperatureState
){

    Column {
        WeatherCurrentInfo(
            weatherInfo = weatherInfo,
            temperatureState = temperatureState
        )

        WeatherForecastInfo(
            weatherInfo = weatherInfo
        )

        WeatherWeekForecastInfo(
            weatherInfo = weatherInfo,
            temperatureState = temperatureState
        )
    }

}
package com.example.weatherapp.features.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.features.data.TemperatureState
import com.example.weatherapp.features.data.WeatherInfo


@Composable
fun WeatherWeekForecastInfo(
    weatherInfo: WeatherInfo,
    temperatureState: TemperatureState
) {
    val weekForecastList = weatherInfo.weekListForecast

    Row(
        modifier = Modifier
            .padding(all = 4.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .horizontalScroll(ScrollState(0)),
    ) {
        for (item in weekForecastList) {
            val minTemperatureValue =
                if (temperatureState == TemperatureState.CELSIUS)
                    stringResource(
                        id = R.string.temperature_info,
                        item.day?.mintempC.toString(),
                        "C"
                    )
                else
                    stringResource(
                        id = R.string.temperature_info,
                        item.day?.mintempF.toString(),
                        "F"
                    )

            val maxTemperatureValue =
                if (temperatureState == TemperatureState.CELSIUS)
                    stringResource(
                        id = R.string.temperature_info,
                        item.day?.maxtempC.toString(),
                        "C"
                    )
                else
                    stringResource(
                        id = R.string.temperature_info,
                        item.day?.maxtempF.toString(),
                        "F"
                    )

            Column(
                modifier = Modifier
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.date.toString().substringAfter("-")
                )
                AsyncImage(
                    modifier = Modifier
                        .size(50.dp),
                    model = stringResource(
                        id = R.string.https,
                        item.day?.condition?.icon.toString()
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = maxTemperatureValue
                )

                Box(
                    modifier = Modifier
                        .padding(
                            top = 4.dp,
                            bottom = 4.dp
                        )
                        .height(80.dp)
                        .width(10.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(Color.Red, Color.Cyan)
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )
                )

                Text(
                    text = minTemperatureValue
                )
            }
        }
    }
}
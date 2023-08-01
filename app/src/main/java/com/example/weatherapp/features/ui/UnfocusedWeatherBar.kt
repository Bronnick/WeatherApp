package com.example.weatherapp.features.ui


import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.features.data.TopBarState
import com.example.weatherapp.features.data.WeatherUiState


@Composable
fun UnfocusedWeatherBar(
    weatherUiState: WeatherUiState,
    onMenuButtonClick: () -> Unit,
    onUnfocusedSearchClick: () -> Unit
) {
    var cityName: String? = null
    var country: String? = null

    if(weatherUiState is WeatherUiState.Success){
        cityName = weatherUiState.weatherInfo.city
        country = weatherUiState.weatherInfo.country
    }

    TopAppBar(
        backgroundColor = Color.Transparent.copy(alpha = 0.1f)
    ) {
        Row {
            IconButton(onClick = {
                onMenuButtonClick()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.weight(1f, true))

            if (cityName != null && country != null) {
                Text(
                    text =
                    stringResource(
                        R.string.location_info,
                        cityName,
                        country
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = onUnfocusedSearchClick) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            }
        }
    }

}
package com.example.weatherapp.features.ui

import androidx.compose.runtime.Composable
import com.example.weatherapp.features.data.TopBarState
import com.example.weatherapp.features.data.WeatherUiState

@Composable
fun MainWeatherBar(
    topBarState: TopBarState,
    weatherUiState: WeatherUiState,
    onMenuButtonClick: () -> Unit,
    onUnfocusedSearchClick: () -> Unit,
    onCancelButtonClick: () -> Unit
) {
    when(topBarState){
        TopBarState.FOCUSED -> FocusedWeatherBar(
            onCancelButtonClick = onCancelButtonClick
        )
        TopBarState.UNFOCUSED -> UnfocusedWeatherBar(
            weatherUiState = weatherUiState,
            onMenuButtonClick = onMenuButtonClick,
            onUnfocusedSearchClick = onUnfocusedSearchClick
        )
    }
}
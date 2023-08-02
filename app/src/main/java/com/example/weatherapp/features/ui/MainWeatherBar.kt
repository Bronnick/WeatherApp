package com.example.weatherapp.features.ui

import androidx.compose.runtime.Composable
import com.example.weatherapp.features.data.TopBarState
import com.example.weatherapp.features.data.WeatherUiState

@Composable
fun MainWeatherBar(
    topBarState: TopBarState,
    weatherUiState: WeatherUiState,
    focusedTextState: String,
    onFocusedTextChange: (String) -> Unit,
    onMenuButtonClick: () -> Unit,
    onUnfocusedSearchClick: () -> Unit,
    onCancelButtonClick: () -> Unit,
    onKeyboardSearchClick: (String) -> Unit
) {
    when(topBarState){
        TopBarState.FOCUSED -> FocusedWeatherBar(
            focusedTextState = focusedTextState,
            onFocusedTextChange = onFocusedTextChange,
            onCancelButtonClick = onCancelButtonClick,
            onKeyboardSearchClick = onKeyboardSearchClick
        )
        TopBarState.UNFOCUSED -> UnfocusedWeatherBar(
            weatherUiState = weatherUiState,
            onMenuButtonClick = onMenuButtonClick,
            onUnfocusedSearchClick = onUnfocusedSearchClick
        )
    }
}
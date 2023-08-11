package com.example.weatherapp.features.ui


import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.features.data.*
import kotlinx.coroutines.launch


@Composable
fun WeatherApp(

) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val weatherViewModel: WeatherViewModel =
        viewModel(factory = WeatherViewModel.Factory)
    val weatherUiState = weatherViewModel.weatherUiState
    val topBarState = weatherViewModel.topBarState
    val temperatureState = weatherViewModel.temperatureState
    val focusedTopBarTextState = weatherViewModel.focusedTopBarTextState
    val backgroundImageState = weatherViewModel.backgroundImageState

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        scaffoldState = scaffoldState,
        drawerContent = {
            Drawer(
                temperatureState = temperatureState,
                backgroundImageState = backgroundImageState,
                onTemperatureSwitch = {
                    weatherViewModel.switchTemperatureState()
                },
                onBackgroundImageSwitch = {
                    weatherViewModel.switchBackgroundImageState()
                }
            )
        },

        topBar = {
            MainWeatherBar(
                topBarState = topBarState,
                weatherUiState = weatherUiState,
                focusedTextState = focusedTopBarTextState,
                onFocusedTextChange = {
                    weatherViewModel.changeFocusedTopBarTextState(it)
                    weatherViewModel.getAutocompleteInfo(it)
                },
                onMenuButtonClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                        //Log.d("myLogs", (weatherUiState as WeatherUiState.Success).weatherInfo.condition?.icon!!)
                    }
                },
                onUnfocusedSearchClick = {
                    weatherViewModel.changeTopBarState(
                        TopBarState.Focused(
                            emptyList()
                        )
                    )
                },
                onCancelButtonClick = {
                    weatherViewModel.changeTopBarState(TopBarState.Unfocused)
                },
                onKeyboardSearchClick = {
                    weatherViewModel.getWeatherInfo(it, 7)
                }
            )
        },
    ) {
        HomeScreen(
            modifier = Modifier.padding(it),
            weatherUiState = weatherUiState,
            temperatureState = temperatureState,
            backgroundImageState = backgroundImageState
        )
    }
}
package com.example.weatherapp.features.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.weatherapp.R
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

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        scaffoldState = scaffoldState,
        drawerContent = {
            Drawer(
                temperatureState = temperatureState,
                onTemperatureSwitch = {
                    weatherViewModel.switchTemperatureState()
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
                        Log.d("myLogs", (weatherUiState as WeatherUiState.Success).weatherInfo.condition?.icon!!)
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
            temperatureState = weatherViewModel.temperatureState
        )
    }
}
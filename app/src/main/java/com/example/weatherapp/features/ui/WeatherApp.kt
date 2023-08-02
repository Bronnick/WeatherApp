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
import com.example.weatherapp.features.data.DrawerState
import com.example.weatherapp.features.data.TopBarState
import com.example.weatherapp.features.data.WeatherUiState
import com.example.weatherapp.features.data.WeatherViewModel
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

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        scaffoldState = scaffoldState,
        drawerContent = {
            Drawer(
                temperatureState = weatherViewModel.temperatureState,
                onTemperatureSwitch = {
                    weatherViewModel.switchTemperatureState()
                }
            )
        },

        topBar = {
            MainWeatherBar(
                topBarState = topBarState,
                weatherUiState = weatherUiState,
                onMenuButtonClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                        Log.d("myLogs", (weatherUiState as WeatherUiState.Success).weatherInfo.condition?.icon!!)
                    }
                },
                onUnfocusedSearchClick = {
                    weatherViewModel.changeTopBarState(TopBarState.FOCUSED)
                },
                onCancelButtonClick = {
                    weatherViewModel.changeTopBarState(TopBarState.UNFOCUSED)
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
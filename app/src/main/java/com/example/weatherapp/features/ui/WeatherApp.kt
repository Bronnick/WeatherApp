package com.example.weatherapp.features.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.weatherapp.R
import com.example.weatherapp.features.data.WeatherUiState
import com.example.weatherapp.features.data.WeatherViewModel


@Composable
fun WeatherApp(

) {
    val weatherViewModel: WeatherViewModel =
        viewModel(factory = WeatherViewModel.Factory)
    val weatherUiState = weatherViewModel.weatherUiState

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HomeScreen(
            modifier = Modifier.padding(it),
            weatherUiState = weatherUiState
        )
    }
}
package com.example.weatherapp.features.data

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.weatherapp.features.repository.NetworkWeatherRepository
import com.example.weatherapp.features.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface WeatherUiState{
    data class Success(val weatherInfo: WeatherInfo) : WeatherUiState
    object Loading : WeatherUiState
    object Error : WeatherUiState
}

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    var weatherUiState : WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    init{
        getWeatherInfo("Kiev")
    }

    fun getWeatherInfo(query: String){
        viewModelScope.launch {
            weatherUiState = WeatherUiState.Loading
            weatherUiState =
                try{
                    WeatherUiState.Success(
                        weatherRepository.getWeatherInfo(query)
                    )
                } catch(e: IOException){
                    WeatherUiState.Error
                } catch(e: HttpException){
                    WeatherUiState.Error
                }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val booksRepository = appContainer.networkWeatherRepository
                WeatherViewModel(booksRepository)
            }
        }
    }

}
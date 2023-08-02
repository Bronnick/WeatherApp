package com.example.weatherapp.features.data

import android.util.Log
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.weatherapp.features.repository.NetworkWeatherRepository
import com.example.weatherapp.features.repository.WeatherRepository
import com.example.weatherapp.features.ui.DrawerItem
import kotlinx.coroutines.launch
import okhttp3.internal.immutableListOf
import retrofit2.HttpException
import java.io.IOException

sealed interface WeatherUiState{
    data class Success(val weatherInfo: WeatherInfo) : WeatherUiState
    object Loading : WeatherUiState
    object Error : WeatherUiState
}

enum class TopBarState{
    FOCUSED,
    UNFOCUSED
}

enum class DrawerState{
    OPENED,
    CLOSED
}

enum class TemperatureState{
    CELSIUS,
    FAHRENHEIT
}

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {
    var weatherUiState : WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    var topBarState: TopBarState by mutableStateOf(TopBarState.UNFOCUSED)
        private set

    var focusedTopBarTextState by mutableStateOf("")
        private set

    var temperatureState: TemperatureState by
        mutableStateOf(TemperatureState.CELSIUS)
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

    fun changeTopBarState(newState: TopBarState){
        topBarState = newState
    }

    fun changeFocusedTopBarTextState(newState: String){
        focusedTopBarTextState = newState
    }

    fun switchTemperatureState(){
        if(temperatureState == TemperatureState.CELSIUS)
            temperatureState = TemperatureState.FAHRENHEIT
        else temperatureState = TemperatureState.CELSIUS
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
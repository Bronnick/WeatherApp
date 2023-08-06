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
    data class Success(
        val weatherInfo: WeatherInfo
    ) : WeatherUiState
    object Loading : WeatherUiState
    object Error : WeatherUiState
}

sealed interface TopBarState{
    data class Focused(
        val autocompleteList: List<AutocompleteInfo>
    ) : TopBarState

    object Unfocused : TopBarState

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

    var topBarState: TopBarState by mutableStateOf(TopBarState.Unfocused)
        private set

    var focusedTopBarTextState by mutableStateOf("")
        private set

    var temperatureState: TemperatureState by
        mutableStateOf(TemperatureState.CELSIUS)
        private set

    init{
        getWeatherInfo("Kiev", 7)
    }

    fun getWeatherInfo(
        query: String,
        numberOfDays: Int
    ) {
        viewModelScope.launch {
            weatherUiState = WeatherUiState.Loading
            weatherUiState =
                try{
                    WeatherUiState.Success(
                        weatherRepository.getWeatherInfo(query, numberOfDays)
                    )
                } catch(e: IOException){
                    WeatherUiState.Error
                } catch(e: HttpException){
                    WeatherUiState.Error
                }
        }
    }

    fun getAutocompleteInfo(
        query: String
    ) {
        viewModelScope.launch {
            topBarState =
                try {
                    TopBarState.Focused(
                        weatherRepository.getSearchInfo(query)
                    )
                } catch(e: IOException){
                    TopBarState.Focused(
                        emptyList()
                    )
                } catch(e: HttpException){
                    TopBarState.Focused(
                        emptyList()
                    )
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
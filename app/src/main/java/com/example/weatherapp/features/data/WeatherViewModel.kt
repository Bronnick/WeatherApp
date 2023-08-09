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
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
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

enum class BackgroundImageState{
    ENABLED,
    DISABLED
}

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    var weatherInfoJob: Job? = null

    var weatherUiState : WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    var topBarState: TopBarState by mutableStateOf(TopBarState.Unfocused)
        private set

    var focusedTopBarTextState by mutableStateOf("")
        private set

    var temperatureState: TemperatureState by
        mutableStateOf(TemperatureState.CELSIUS)
        private set

    var backgroundImageState: BackgroundImageState by
        mutableStateOf(BackgroundImageState.ENABLED)
        private set

    init{
        getWeatherInfo("Kiev", 7)
    }

    fun getWeatherInfo(
        query: String,
        numberOfDays: Int
    ) {
        val stopGetWeatherJob = viewModelScope.launch {
            weatherInfoJob?.cancelAndJoin()
        }

        weatherInfoJob = viewModelScope.launch {
            stopGetWeatherJob.join()
            weatherUiState = WeatherUiState.Loading
            do {
                weatherUiState =
                    try {
                        WeatherUiState.Success(
                            weatherRepository.getWeatherInfo(query, numberOfDays)
                        )
                    } catch (e: IOException) {
                        WeatherUiState.Error
                    } catch (e: HttpException) {
                        WeatherUiState.Error
                    }
            } while(weatherUiState == WeatherUiState.Error)
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

    fun switchBackgroundImageState(){
        if(backgroundImageState == BackgroundImageState.ENABLED)
            backgroundImageState = BackgroundImageState.DISABLED
        else backgroundImageState = BackgroundImageState.ENABLED
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
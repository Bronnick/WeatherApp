package com.example.weatherapp.features.data


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.weatherapp.features.repository.WeatherRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import com.example.weatherapp.R
import com.example.weatherapp.features.data.classes.AutocompleteInfo
import com.example.weatherapp.features.data.classes.WeatherInfo

sealed interface WeatherUiState{
    data class Success(
        val weatherInfo: WeatherInfo
    ) : WeatherUiState
    object Loading : WeatherUiState
    data class Error(
        val errorIconId: Int,
        val messageId: Int
    ) : WeatherUiState
}

sealed interface TopBarState{
    data class Focused(
        val autocompleteList: List<AutocompleteInfo>
    ) : TopBarState

    object Unfocused : TopBarState

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

    private var weatherInfoJob: Job? = null

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
                        WeatherUiState.Error(
                            errorIconId = R.drawable.baseline_wifi_off_24,
                            messageId = R.string.internet_connection_issue
                        )
                    } catch (e: HttpException) {
                        WeatherUiState.Error(
                            errorIconId = R.drawable.baseline_location_off_24,
                            messageId = R.string.location_issue
                        )
                    }
            } while(weatherUiState is WeatherUiState.Error)
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
        temperatureState = if(temperatureState == TemperatureState.CELSIUS)
            TemperatureState.FAHRENHEIT
            else TemperatureState.CELSIUS
    }

    fun switchBackgroundImageState(){
        backgroundImageState = if(backgroundImageState == BackgroundImageState.ENABLED)
            BackgroundImageState.DISABLED
            else BackgroundImageState.ENABLED
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
package com.example.weatherapp.features.data

import android.util.Log
import androidx.compose.material.ScaffoldState
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
import kotlinx.coroutines.launch
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

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {
    var weatherUiState : WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    var topBarState: TopBarState by mutableStateOf(TopBarState.UNFOCUSED)
        private set

    val drawerState: MutableLiveData<DrawerState> =
        MutableLiveData(DrawerState.CLOSED)

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

    fun changeDrawerState(newState: DrawerState){
        drawerState.value = newState
        Log.d("myLogs", "Drawer state changed")
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
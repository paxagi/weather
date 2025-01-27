package com.example.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.City
import com.example.weather.data.WeatherRepository
import com.example.weather.domain.WeatherDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
): ViewModel() {
    private val _weatherData = MutableLiveData<WeatherDomain>()
    val weatherData: LiveData<WeatherDomain> = _weatherData

    fun fetchWeatherData(city: City, apiKey: String) {
        viewModelScope.launch {
            _weatherData.value = repository.getCurrentWeather(city, apiKey)
        }
    }
}


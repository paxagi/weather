package com.example.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.City
import com.example.weather.data.FavoriteCitiesRepository
import com.example.weather.data.WeatherRepository
import com.example.weather.domain.WeatherDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val favoriteCitiesRepository: FavoriteCitiesRepository,
): ViewModel() {
    private val _weatherData = MutableLiveData<WeatherDomain>()
    val weatherData: LiveData<WeatherDomain> = _weatherData

    private val _favoriteCities = MutableLiveData<List<City>>()
    val favoriteCities: LiveData<List<City>> = _favoriteCities

    fun fetchWeatherData(city: City, apiKey: String) {
        viewModelScope.launch {
            _weatherData.value = weatherRepository.getCurrentWeather(city, apiKey)
        }
    }

    fun loadFavoriteCities() {
        viewModelScope.launch {
            _favoriteCities.value = favoriteCitiesRepository.getAll()
        }
    }

    fun addFavoriteCity(city: City) {
        viewModelScope.launch {
            favoriteCitiesRepository.insert(city)
            loadFavoriteCities()
        }
    }

    fun removeFavoriteCity(city: City) {
        viewModelScope.launch {
            favoriteCitiesRepository.delete(city.name)
            loadFavoriteCities()
        }
    }
}


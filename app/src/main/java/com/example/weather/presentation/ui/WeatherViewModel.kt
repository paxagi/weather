package com.example.weather.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.model.Weather
import com.example.weather.domain.repository.FavoriteCitiesRepository
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.presentation.mapper.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val favoriteCitiesRepository: FavoriteCitiesRepository,
): ViewModel() {
    private val _cityIsFavorite = MutableLiveData<Boolean>()
    var cityIsFavorite: LiveData<Boolean> = _cityIsFavorite

    private val _fetchedProblem = MutableLiveData<Boolean>()
    var fetchedProblem: LiveData<Boolean> = _fetchedProblem

    private val _favoriteCities = MutableLiveData<MutableList<WeatherItem>>()
    val favoriteCities: LiveData<MutableList<WeatherItem>> = _favoriteCities

    private val _weatherData = MutableLiveData<Weather?>()
    val weatherData: LiveData<Weather?>
        get() {
            val result = _weatherData
            _weatherData.value = null
            return result
        }

    fun fetchWeatherData(city: String) {
        viewModelScope.launch {
            _weatherData.value = weatherRepository.getCurrentWeather(city)
            _fetchedProblem.value = _weatherData.value == null
        }
    }

    fun isFavorite(city: String) {
        viewModelScope.launch {
            _cityIsFavorite.value = favoriteCitiesRepository.exists(city)
        }
    }

    fun loadFavoriteCities(): Job {
        return viewModelScope.launch {
            val cityList = favoriteCitiesRepository.getAll()
            val updatedCities = mutableListOf<WeatherItem>()
            for (city in cityList) {
                val weather = withContext(Dispatchers.IO) {
                    weatherRepository.getCurrentWeather(city)
                }
                weather?.let {
                    updatedCities.add(it.toUI())
                }
            }
            _favoriteCities.value = updatedCities
        }
    }

    fun addOrRemoveFavoriteCity(city: String) {
        viewModelScope.launch {
            loadFavoriteCities().join()
            if (favoriteCitiesRepository.exists(city)) {
                favoriteCitiesRepository.delete(city)
            } else {
                favoriteCitiesRepository.insert(city)
            }
            isFavorite(city)
            loadFavoriteCities().join()
        }
    }
}


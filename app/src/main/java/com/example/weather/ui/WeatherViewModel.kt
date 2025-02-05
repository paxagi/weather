package com.example.weather.ui

import ApiKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.City
import com.example.weather.data.FavoriteCitiesRepository
import com.example.weather.data.WeatherRepository
import com.example.weather.domain.WeatherDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val favoriteCitiesRepository: FavoriteCitiesRepository,
): ViewModel() {
    private val _cityIsFavorite = MutableLiveData<Boolean>()
    var cityIsFavorite: LiveData<Boolean> = _cityIsFavorite
    private val _favoriteCities = MutableLiveData<MutableList<WeatherItem>>()
    val favoriteCities: LiveData<MutableList<WeatherItem>> = _favoriteCities

    private val _weatherData = MutableLiveData<WeatherDomain?>()
    val weatherData: LiveData<WeatherDomain?>
        get() {
            val result = _weatherData
            _weatherData.value = null
            return result
        }

    fun fetchWeatherData(city: City, apiKey: String) {
        viewModelScope.launch {
            _weatherData.value = weatherRepository.getCurrentWeather(city, apiKey)
        }
    }

    fun isFavorite(city: City) {
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
                    weatherRepository.getCurrentWeather(city, ApiKey.KEY)
                }
                weather?.let {
                    updatedCities.add(it.toUI())
                }
            }
            _favoriteCities.value = updatedCities
        }
    }

    fun addOrRemoveFavoriteCity(city: City) {
        viewModelScope.launch {
            loadFavoriteCities().join()
            if (favoriteCitiesRepository.exists(city)) {
                favoriteCitiesRepository.delete(city.name)
            } else {
                favoriteCitiesRepository.insert(city)
            }
            isFavorite(city)
            loadFavoriteCities().join()
        }
    }
}


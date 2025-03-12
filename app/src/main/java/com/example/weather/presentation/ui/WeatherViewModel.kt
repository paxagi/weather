package com.example.weather.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.model.Weather
import com.example.weather.domain.usecase.GetWeatherUseCase
import com.example.weather.domain.usecase.AddOrRemoveFavoriteCityUseCase
import com.example.weather.domain.usecase.GetFavoriteCitiesUseCase
import com.example.weather.domain.usecase.IsCityFavoriteUseCase
import com.example.weather.presentation.mapper.toUI
import com.example.weather.presentation.model.WeatherItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val isCityFavoriteUseCase: IsCityFavoriteUseCase,
    private val getFavoriteCitiesUseCase: GetFavoriteCitiesUseCase,
    private val addOrRemoveFavoriteCityUseCase: AddOrRemoveFavoriteCityUseCase,
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
            _weatherData.value = getWeatherUseCase(city)
            _fetchedProblem.value = _weatherData.value == null
        }
    }

    fun isFavorite(city: String) {
        viewModelScope.launch {
            _cityIsFavorite.value = isCityFavoriteUseCase(city)
        }
    }

    fun fetchFavoriteCitiesData(): Job {
        return viewModelScope.launch {
            val favoriteCities = withContext(Dispatchers.IO) { getFavoriteCitiesUseCase() }
            val updatedCities = favoriteCities.map { it.toUI() }
            _favoriteCities.value = updatedCities as MutableList<WeatherItem>
        }
    }

    fun addOrRemoveFavoriteCity(city: String) {
        viewModelScope.launch {
            addOrRemoveFavoriteCityUseCase(city)
            isFavorite(city)
            fetchFavoriteCitiesData().join()
        }
    }
}


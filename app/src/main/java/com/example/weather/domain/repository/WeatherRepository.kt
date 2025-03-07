package com.example.weather.domain.repository

import com.example.weather.domain.model.City
import com.example.weather.domain.model.Weather

interface WeatherRepository {
    suspend fun getCurrentWeather(city: City): Weather?
}

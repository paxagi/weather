package com.example.weather.domain.repository

import com.example.weather.domain.model.Weather

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): Weather?
}

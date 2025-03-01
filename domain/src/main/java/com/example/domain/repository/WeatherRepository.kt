package com.example.domain.repository

import com.example.domain.model.City
import com.example.domain.model.Weather

interface WeatherRepository {
    suspend fun getCurrentWeather(city: City): Weather?
}

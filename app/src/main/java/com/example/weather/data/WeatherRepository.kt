package com.example.weather.data

import com.example.weather.domain.WeatherDomain
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: WeatherApiService,
) {
    suspend fun getCurrentWeather(city: City, apiKey: String): WeatherDomain? {
         return apiService.getCurrentWeather(city.name, apiKey).body()?.toDomain()
    }
}
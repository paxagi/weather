package com.example.weather.data

import com.example.weather.domain.WeatherDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: WeatherApiService,
) {
    suspend fun getCurrentWeather(city: City, apiKey: String): WeatherDomain? {
        return withContext(Dispatchers.IO) {
            apiService.getCurrentWeather(city.name, apiKey).body()?.toDomain()
        }
    }
}
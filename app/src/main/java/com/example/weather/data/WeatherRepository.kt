package com.example.weather.data

import com.example.weather.domain.WeatherDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: WeatherApiService,
) {
    suspend fun getCurrentWeather(city: City, apiKey: String): WeatherDomain? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCurrentWeather(city.name, apiKey)
                if (response.isSuccessful) {
                    response.body()?.toDomain()
                } else {
                    null
                }
            } catch (e: IOException) {
                null
            } catch (e: HttpException) {
                null
            }
        }
    }
}
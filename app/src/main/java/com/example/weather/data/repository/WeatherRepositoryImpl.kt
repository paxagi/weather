package com.example.weather.data.repository

import com.example.weather.data.mapper.toDomain
import com.example.weather.domain.model.Weather
import com.example.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class WeatherRepositoryImpl @Inject constructor(
    private val apiService: com.example.weather.data.remote.WeatherApiService,
) : WeatherRepository {
    override suspend fun getCurrentWeather(city: String): Weather? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCurrentWeather(city)
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
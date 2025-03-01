package com.example.data.repository

import com.example.domain.model.City
import com.example.data.mapper.toDomain
import com.example.data.remote.WeatherApiService
import com.example.domain.model.Weather
import com.example.domain.repository.WeatherRepository
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService,
) : WeatherRepository {
    override suspend fun getCurrentWeather(city: City): Weather? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCurrentWeather(city.name)
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
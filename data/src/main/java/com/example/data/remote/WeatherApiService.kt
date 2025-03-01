package com.example.data.remote

import com.example.data.WeatherDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru",
    ): Response<WeatherDTO> //TODO: use resources and multilanguage
} // api call weather?q={city name}&appid={API key}

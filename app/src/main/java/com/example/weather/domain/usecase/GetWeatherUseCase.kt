package com.example.weather.domain.usecase

import com.example.weather.domain.model.Weather
import com.example.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Weather? {
        return weatherRepository.getCurrentWeather(city)
    }
}

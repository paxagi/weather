package com.example.weather.domain.usecase

import com.example.weather.domain.model.Weather
import com.example.weather.domain.repository.FavoriteCitiesRepository
import com.example.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetFavoriteCitiesUseCase @Inject constructor(
    private val favoriteCitiesRepository: FavoriteCitiesRepository,
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(): List<Weather> {
        val favoriteCities = favoriteCitiesRepository.getAll()
        val result = mutableListOf<Weather>()

        for (city in favoriteCities) {
            val weather = weatherRepository.getCurrentWeather(city)
            weather?.let { result.add(it) }
        }

        return result
    }
}

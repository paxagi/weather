package com.example.weather.domain.usecase

import com.example.weather.domain.repository.FavoriteCitiesRepository
import javax.inject.Inject

class AddOrRemoveFavoriteCityUseCase @Inject constructor(
    private val favoriteCitiesRepository: FavoriteCitiesRepository
) {
    suspend operator fun invoke(city: String) {
        if (favoriteCitiesRepository.exists(city)) {
            favoriteCitiesRepository.delete(city)
        } else {
            favoriteCitiesRepository.insert(city)
        }
    }
}

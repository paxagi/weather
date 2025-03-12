package com.example.weather.domain.usecase

import com.example.weather.domain.repository.FavoriteCitiesRepository
import javax.inject.Inject

class IsCityFavoriteUseCase @Inject constructor(
    private val favoriteCitiesRepository: FavoriteCitiesRepository
) {
    suspend operator fun invoke(city: String): Boolean {
        return favoriteCitiesRepository.exists(city)
    }
}

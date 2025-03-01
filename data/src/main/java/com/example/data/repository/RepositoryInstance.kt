package com.example.data.repository

import com.example.domain.repository.FavoriteCitiesRepository
import com.example.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFavoriteCitiesRepository(repository: FavoriteCitiesRepositoryImpl): FavoriteCitiesRepository

    @Binds
    abstract fun bindWeatherRepository(repository: WeatherRepositoryImpl): WeatherRepository
}

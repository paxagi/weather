package com.example.weather.domain.repository

import com.example.weather.domain.model.City

interface FavoriteCitiesRepository {
    suspend fun insert(city: City)
    suspend fun getAll(): List<City>
    suspend fun delete(cityName: String)
    suspend fun exists(city: City): Boolean
}

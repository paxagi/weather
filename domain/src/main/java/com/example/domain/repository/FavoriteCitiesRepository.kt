package com.example.domain.repository

import com.example.domain.model.City

interface FavoriteCitiesRepository {
    suspend fun insert(city: City)
    suspend fun getAll(): List<City>
    suspend fun delete(cityName: String)
    suspend fun exists(city: City): Boolean
}

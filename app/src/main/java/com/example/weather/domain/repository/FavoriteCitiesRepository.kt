package com.example.weather.domain.repository

interface FavoriteCitiesRepository {
    suspend fun insert(city: String)
    suspend fun getAll(): List<String>
    suspend fun delete(cityName: String)
    suspend fun exists(city: String): Boolean
}

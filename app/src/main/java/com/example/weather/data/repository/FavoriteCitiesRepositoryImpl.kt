package com.example.weather.data.repository

import com.example.weather.data.local.FavoriteCitiesDao
import com.example.weather.data.local.FavoriteCityEntity
import com.example.weather.domain.repository.FavoriteCitiesRepository
import javax.inject.Inject

internal class FavoriteCitiesRepositoryImpl @Inject constructor(private val dao: FavoriteCitiesDao) :
    FavoriteCitiesRepository {
    override suspend fun insert(city: String) = dao.insert(FavoriteCityEntity(city))
    override suspend fun getAll(): List<String> = dao.getAll().map { it.city }
    override suspend fun delete(cityName: String) = dao.delete(cityName)
    override suspend fun exists(city: String): Boolean = dao.exists(city)
}
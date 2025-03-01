package com.example.data.repository

import com.example.data.local.FavoriteCitiesDao
import com.example.data.local.FavoriteCityEntity
import com.example.domain.model.City
import com.example.domain.repository.FavoriteCitiesRepository
import javax.inject.Inject

class FavoriteCitiesRepositoryImpl @Inject constructor(private val dao: FavoriteCitiesDao) : FavoriteCitiesRepository {
    override suspend fun insert(city: City) = dao.insert(FavoriteCityEntity(city.name))
    override suspend fun getAll(): List<City> = dao.getAll().map { City(it.city) }
    override suspend fun delete(cityName: String) = dao.delete(cityName)
    override suspend fun exists(city: City): Boolean = dao.exists(city.name)
}
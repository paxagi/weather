package com.example.weather.data.repository

import com.example.domain.City
import com.example.weather.data.FavoriteCity
import com.example.weather.data.local.FavoriteCitiesDao
import javax.inject.Inject

class FavoriteCitiesRepository @Inject constructor(private val dao: FavoriteCitiesDao) {
    suspend fun insert(city: City) = dao.insert(FavoriteCity(city.name))
    suspend fun getAll(): List<City> = dao.getAll().map { City(it.city) }
    suspend fun delete(cityName: String) = dao.delete(cityName)
    suspend fun exists(city: City): Boolean = dao.exists(city.name)
}

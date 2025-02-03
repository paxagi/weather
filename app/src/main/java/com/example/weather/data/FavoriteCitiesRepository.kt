package com.example.weather.data

import javax.inject.Inject

class FavoriteCitiesRepository @Inject constructor(private val dao: FavoriteCitiesDao) {
    suspend fun insert(city: City) = dao.insert(FavoriteCity(city.name))
    suspend fun getAll(): List<City> = dao.getAll().map { City(it.city) }
    suspend fun delete(cityName: String) = dao.delete(cityName)
}

package com.example.weather.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.data.FavoriteCity

@Dao
interface FavoriteCitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: FavoriteCity)

    @Query("SELECT * FROM favorite_cities")
    suspend fun getAll(): List<FavoriteCity>

    @Query("DELETE FROM favorite_cities WHERE city = :cityName")
    suspend fun delete(cityName: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_cities WHERE city = :cityName)")
    suspend fun exists(cityName: String): Boolean
}

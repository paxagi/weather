package com.example.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.data.FavoriteCity

@Database(entities = [FavoriteCity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCitiesDao(): FavoriteCitiesDao
}

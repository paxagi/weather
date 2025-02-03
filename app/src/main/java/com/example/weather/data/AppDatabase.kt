package com.example.weather.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteCity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCitiesDao(): FavoriteCitiesDao
}

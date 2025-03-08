package com.example.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteCityEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCitiesDao(): FavoriteCitiesDao
}

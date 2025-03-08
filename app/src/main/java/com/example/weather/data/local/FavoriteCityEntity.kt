package com.example.weather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_cities")
internal data class FavoriteCityEntity(
    @PrimaryKey val city: String
)

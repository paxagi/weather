package com.example.weather.data

data class Weather(
    val temperature: Float,
    val feelsLike: Float,
    val temperatureMin: Float,
    val temperatureMax: Float,
    val pressure: Int,
    val humidity: Int,
    val description: String,
    val latitude: Float,
    val longitude: Float,
    val city: String,
)

data class City(
    val latitude: Float,
    val longitude: Float,
    val city: String,
)
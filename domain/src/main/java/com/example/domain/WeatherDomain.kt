package com.example.domain

data class WeatherDomain(
    val city: City,
    val temperature: Float,
    val feelsLike: Float,
    val temperatureMin: Float,
    val temperatureMax: Float,
    val pressure: Int,
    val humidity: Int,
    val description: String,
)

data class City(val name: String)
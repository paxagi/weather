package com.example.weather.domain

data class WeatherDomain(
    val temperature: Float,
    val feelsLike: Float,
    val temperatureMin: Float,
    val temperatureMax: Float,
    val pressure: Int,
    val humidity: Int,
    val description: String,
)
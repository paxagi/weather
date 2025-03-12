package com.example.weather.domain.model

data class Weather(
    val city: String,
    val temperature: Float,
    val feelsLike: Float,
    val temperatureMin: Float,
    val temperatureMax: Float,
    val pressure: Int,
    val humidity: Int,
    val description: String,
)
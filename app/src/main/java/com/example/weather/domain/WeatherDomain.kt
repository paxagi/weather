package com.example.weather.domain

import com.example.weather.data.City
import com.example.weather.ui.WeatherItem

data class WeatherDomain(
    val city: City,
    val temperature: Float,
    val feelsLike: Float,
    val temperatureMin: Float,
    val temperatureMax: Float,
    val pressure: Int,
    val humidity: Int,
    val description: String,
) {
    fun toUI() = WeatherItem(city.name, temperature.toString())
}
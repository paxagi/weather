package com.example.weather.mapper

import com.example.domain.model.Weather
import com.example.weather.ui.WeatherItem

fun Weather.toUI() = WeatherItem(
    city.name,
    temperature.toString(),
    feelsLike.toString(),
    temperatureMin.toString(),
    temperatureMax.toString(),
    pressure.toString(),
    humidity.toString(),
    description,
)

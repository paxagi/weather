package com.example.weather.presentation.mapper

import com.example.weather.domain.model.Weather
import com.example.weather.presentation.ui.WeatherItem

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

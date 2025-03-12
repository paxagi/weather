package com.example.weather.presentation.mapper

import com.example.weather.domain.model.Weather
import com.example.weather.presentation.ui.model.WeatherItem

internal fun Weather.toUI() = WeatherItem(
    city,
    temperature.toString(),
    feelsLike.toString(),
    temperatureMin.toString(),
    temperatureMax.toString(),
    pressure.toString(),
    humidity.toString(),
    description,
)

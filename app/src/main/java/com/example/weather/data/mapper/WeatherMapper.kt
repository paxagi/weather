package com.example.weather.data.mapper

import com.example.domain.City
import com.example.weather.data.WeatherDTO
import com.example.weather.ui.WeatherItem

fun WeatherDTO.toDomain(): com.example.domain.WeatherDomain {
    return com.example.domain.WeatherDomain(
        City(name),
        main.temperature,
        main.feelsLike,
        main.temperatureMin,
        main.temperatureMin,
        main.pressure,
        main.humidity,
        weather[0].description,
    )
}

fun com.example.domain.WeatherDomain.toUI() = WeatherItem(
    city.name,
    temperature.toString(),
    feelsLike.toString(),
    temperatureMin.toString(),
    temperatureMax.toString(),
    pressure.toString(),
    humidity.toString(),
    description,
)

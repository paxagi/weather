package com.example.data.mapper

import com.example.domain.model.City
import com.example.domain.model.Weather
import com.example.data.WeatherDTO

fun WeatherDTO.toDomain(): Weather {
    return Weather(
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

package com.example.weather.data.mapper

import com.example.weather.data.WeatherDTO
import com.example.weather.domain.model.Weather

internal fun WeatherDTO.toDomain(): Weather {
    return Weather(
        name,
        main.temperature,
        main.feelsLike,
        main.temperatureMin,
        main.temperatureMin,
        main.pressure,
        main.humidity,
        weather[0].description,
    )
}

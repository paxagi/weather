package com.example.weather.data

import com.example.weather.domain.WeatherDomain
import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    val name: String,
    val main: Main,
    val weather: List<WeatherItem>,
) {
    fun toDomain() = WeatherDomain(
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

data class Main(
    @SerializedName("temp") val temperature: Float,
    @SerializedName("feels_like") val feelsLike: Float,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("temp_min") val temperatureMin: Float,
    @SerializedName("temp_max") val temperatureMax: Float,
)

data class WeatherItem(
    val description: String,
)

data class City(val name: String)
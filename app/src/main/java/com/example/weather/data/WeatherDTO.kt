package com.example.weather.data

import com.google.gson.annotations.SerializedName

internal data class WeatherDTO(
    val name: String,
    val main: Main,
    val weather: List<WeatherItem>,
)

internal data class Main(
    @SerializedName("temp") val temperature: Float,
    @SerializedName("feels_like") val feelsLike: Float,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("temp_min") val temperatureMin: Float,
    @SerializedName("temp_max") val temperatureMax: Float,
)

internal data class WeatherItem(
    val description: String,
)
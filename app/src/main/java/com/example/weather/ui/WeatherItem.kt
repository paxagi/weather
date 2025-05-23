package com.example.weather.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherItem(
    val cityName: String,
    val temperature: String,
    val feelsLike: String,
    val temperatureMin: String,
    val temperatureMax: String,
    val pressure: String,
    val humidity: String,
    val description: String,
): Parcelable

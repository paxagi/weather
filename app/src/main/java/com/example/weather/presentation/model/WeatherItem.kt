package com.example.weather.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class WeatherItem(
    val cityName: String,
    val temperature: String,
    val feelsLike: String,
    val temperatureMin: String,
    val temperatureMax: String,
    val pressure: String,
    val humidity: String,
    val description: String,
): Parcelable

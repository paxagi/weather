package com.example.weather.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherItem(
    val cityName: String,
    val temperature: String
): Parcelable

package com.example.weather

import ApiKey
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.data.City
import com.example.weather.ui.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiKey = ApiKey.KEY
        weatherViewModel.fetchWeatherData(City("Nizhniy Novgorod"), apiKey)
    }
}
package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.weather.databinding.FragmentWeatherDetailsBinding


class WeatherDetailsFragment : Fragment() {

    private val navArgs: WeatherDetailsFragmentArgs by navArgs()

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weather: WeatherItem = navArgs.weather
        val textViewCity: TextView = binding.textViewCity
        val textViewTemperature: TextView = binding.textViewTemperature

        textViewCity.text = weather.cityName
        textViewTemperature.text = "${weather.temperature}°C"
    }

}
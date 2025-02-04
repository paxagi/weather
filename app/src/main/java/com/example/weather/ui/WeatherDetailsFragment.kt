package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.weather.R
import com.example.weather.data.City
import com.example.weather.databinding.FragmentWeatherDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailsFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModels()
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
        val buttonAddToFavorites: Button = binding.buttonAddOrRemoveToFavorites

        weatherViewModel.cityIsFavorite.observe(viewLifecycleOwner) { isExits ->
            isExits?.let {
                binding.buttonAddOrRemoveToFavorites.text =
                    if (isExits) getString(R.string.fav_remove_btn_label)
                    else getString(R.string.fav_add_btn_label)
            }
        }
        weatherViewModel.isFavorite(City(weather.cityName))

        buttonAddToFavorites.setOnClickListener {
            weatherViewModel.addOrRemoveFavoriteCity(City(weather.cityName))
        }

        textViewCity.text = weather.cityName
        textViewTemperature.text = "${weather.temperature}°C"
    }

}
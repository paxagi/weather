package com.example.weather.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weather.databinding.FragmentSearchCityBinding
import com.example.weather.presentation.mapper.toUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SearchCityFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    private var _binding: FragmentSearchCityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherViewModel.weatherData.observe(viewLifecycleOwner) { weather ->
            weather?.let {
                val action =
                    SearchCityFragmentDirections.actionSearchCityFragmentToWeatherDetailsFragment(
                        weather.toUI()
                    )
                findNavController().navigate(action)
            }
        }

        val editTextCity: EditText = binding.editTextCity
        val buttonSearch: Button = binding.buttonSearch

        buttonSearch.setOnClickListener {
                val cityName = editTextCity.text.toString().trim()
                weatherViewModel.fetchWeatherData(cityName)
        }
    }
}
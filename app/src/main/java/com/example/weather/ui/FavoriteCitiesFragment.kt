package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteCitiesFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        weatherViewModel.favoriteCities.observe(viewLifecycleOwner) { favoriteCities ->
            val weatherList = favoriteCities.map {
                WeatherItem("NN", ">0", )
            }
            recyclerView.adapter = WeatherRecyclerViewAdapter(weatherList) { weather ->
                FavoriteCitiesFragmentDirections
                    .actionFavoriteCitiesFragmentToWeatherDetailsFragment(weather)
                    .let {
                        findNavController().navigate(it)
                    }
            }
        }

        weatherViewModel.loadFavoriteCities()
    }
}

package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        val emptyTextView: TextView = view.findViewById(R.id.emptyTextView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        weatherViewModel.favoriteCities.observe(viewLifecycleOwner) { weatherList ->
            if (weatherList.isEmpty()) {
                recyclerView.visibility = View.GONE
                emptyTextView.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                emptyTextView.visibility = View.GONE
                recyclerView.adapter = WeatherRecyclerViewAdapter(weatherList) { weather ->
                    FavoriteCitiesFragmentDirections
                        .actionFavoriteCitiesFragmentToWeatherDetailsFragment(weather)
                        .let {
                            findNavController().navigate(it)
                        }
                }
            }
        }
        weatherViewModel.loadFavoriteCities()
    }
}

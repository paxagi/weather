package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.weather.databinding.FragmentSearchCityBinding

class SearchCityFragment : Fragment() {

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

        val editTextCity: EditText = binding.editTextCity
        val buttonSearch: Button = binding.buttonSearch
        val buttonAddToFavorites: Button = binding.buttonAddToFavorites

        buttonSearch.setOnClickListener {
            // Поиск города
            val cityName = editTextCity.text.toString()
            // Вызов API для получения данных о погоде для города
        }

        buttonAddToFavorites.setOnClickListener {
            val cityName = editTextCity.text.toString()
            // Добавить город в список избранных
        }
    }
}
package com.example.weather.presentation.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.weather.databinding.WeatherItemBinding


internal class WeatherRecyclerViewAdapter(
    private val weatherList: List<WeatherItem>,
    private val itemClickListener: (WeatherItem) -> Unit,
) : RecyclerView.Adapter<WeatherRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val textViewCity: TextView = binding.textViewCity
        val textViewTemperature: TextView = binding.textViewTemperature
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            WeatherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.textViewCity.text = weather.cityName
        holder.textViewTemperature.text = "${weather.temperature}°C"
        holder.itemView.setOnClickListener { itemClickListener(weather) }
    }

    override fun getItemCount(): Int = weatherList.size

}
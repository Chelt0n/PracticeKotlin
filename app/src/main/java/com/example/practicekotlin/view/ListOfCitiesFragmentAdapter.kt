package com.example.practicekotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.practicekotlin.R
import com.example.practicekotlin.domain.Weather

class ListOfCitiesFragmentAdapter :
    RecyclerView.Adapter<ListOfCitiesFragmentAdapter.MainViewHolder>() {
    private var weatherData: List<Weather> = listOf()
    private lateinit var listener: OnClickWeather

    fun setWeather(data: List<Weather>) {
        weatherData = data
        notifyDataSetChanged()
    }

    fun setOnClickWeather(onClickWeather: OnClickWeather) {
        listener = onClickWeather
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_fragment_recycler_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.render(weatherData[position])
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun render(weather: Weather) {
            itemView.findViewById<TextView>(R.id.mainFragmentRecyclerItemTextView).text =
                weather.city.name
            itemView.setOnClickListener {
                listener.onClick(weather)
                Toast.makeText(
                    itemView.context,
                    "работает",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}
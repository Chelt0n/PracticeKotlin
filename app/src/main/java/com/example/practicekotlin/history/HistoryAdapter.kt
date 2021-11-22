package com.example.practicekotlin.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.practicekotlin.R
import com.example.practicekotlin.domain.Weather


class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var weatherData: List<Weather> = listOf()
    fun setWeather(data: List<Weather>) {
        weatherData = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_history_recycler_view_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.render(weatherData[position])
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun render(weather: Weather) {
            itemView.findViewById<TextView>(R.id.historyFragmentRecyclerItemTextView).text =
                weather.city.name

        }

    }
}
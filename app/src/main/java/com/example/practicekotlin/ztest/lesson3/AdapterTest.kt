package com.example.practicekotlin.ztest.lesson3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicekotlin.R
import com.example.practicekotlin.databinding.TestListBinding
import com.example.practicekotlin.domain.Weather

class AdapterTest : RecyclerView.Adapter<AdapterTest.MyViewHolder>() {
    private var data: List<Weather> = listOf()
    fun getData(weather: List<Weather>) {
        data = weather
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.test_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.render(data[position])

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = TestListBinding.bind(itemView)
        fun render(weather: Weather) {
            binding.textTest.text = weather.city.name
        }

    }
}
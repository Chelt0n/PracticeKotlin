package com.example.practicekotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practicekotlin.databinding.DetailsFragmentBinding
import com.example.practicekotlin.domain.Weather
class DetailsWeatherFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = (arguments?.getParcelable<Weather>(WEATHER_KEY)) ?: Weather()
        fillInAllFields(weather)


    }

    private fun fillInAllFields(weather: Weather) {
        binding.cityName.text = weather.city.name
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeValue.text = weather.feelsLike.toString()
    }


    companion object {
        fun newInstance(bundle: Bundle): DetailsWeatherFragment {
            val fragment = DetailsWeatherFragment()
            fragment.arguments = bundle
            return fragment
        }

        const val WEATHER_KEY = "KEY"
    }


}
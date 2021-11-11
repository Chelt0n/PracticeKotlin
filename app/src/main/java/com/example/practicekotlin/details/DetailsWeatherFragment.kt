package com.example.practicekotlin.details


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.practicekotlin.databinding.DetailsFragmentBinding
import com.example.practicekotlin.domain.Weather
import com.example.practicekotlin.repository.WeatherDTO
import com.example.practicekotlin.utils.API_URL
import com.example.practicekotlin.viewmodel.AppState
import com.example.practicekotlin.viewmodel.DetailsViewModel
import com.google.android.material.snackbar.Snackbar
import okhttp3.*

class DetailsWeatherFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this)[DetailsViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private val localWeather: Weather by lazy {
        (arguments?.getParcelable<Weather>(WEATHER_KEY)) ?: Weather()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, {
            render(it)
        })
        val coordinates = "lat=${localWeather.city.lat}&lon${localWeather.city.lon}"
        viewModel.getWeatherFromRemoteSource(API_URL + coordinates)


    }

    private fun render(appState: AppState) {
        when (appState) {
            AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
                binding.mainView.visibility = View.GONE
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.INVISIBLE
                binding.mainView.visibility = View.VISIBLE
                val weather = appState.weatherData
                fillInAllFields(weather[0])
            }

            is AppState.Error -> {
                binding.loadingLayout.visibility = View.INVISIBLE
                binding.mainView.visibility = View.VISIBLE
                val throwable = appState.error
                Snackbar.make(binding.root, "Ошибка+${throwable}", Snackbar.LENGTH_LONG).show()
            }
        }

    }


    private fun fillInAllFields(weather: Weather) {
        with(binding) {
            cityName.text = localWeather.city.name
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()
            weatherCondition.text = weather.condition
        }

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
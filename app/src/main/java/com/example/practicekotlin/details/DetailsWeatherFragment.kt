package com.example.practicekotlin.details


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.bumptech.glide.Glide
import com.example.practicekotlin.databinding.DetailsFragmentBinding
import com.example.practicekotlin.domain.Weather
import com.example.practicekotlin.viewmodel.AppState
import com.example.practicekotlin.viewmodel.DetailsViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

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
        (arguments?.getParcelable(WEATHER_KEY)) ?: Weather()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, {
            render(it)
        })
        viewModel.getWeatherFromRemoteSource(localWeather.city.lat, localWeather.city.lon)



    }

    private fun render(appState: AppState) {
        when (appState) {
            AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
                binding.mainView.visibility = View.GONE
                Log.d("MyLog","Loading")
            }
            is AppState.SuccessDetails -> {
                Snackbar.make(binding.root, "success", Snackbar.LENGTH_LONG).show()
                binding.loadingLayout.visibility = View.INVISIBLE
                binding.mainView.visibility = View.VISIBLE
                val weather = appState.weatherData
                fillInAllFields(weather)
                Log.d("MyLog","SuccessDetails")
            }

            is AppState.Error -> {
                binding.loadingLayout.visibility = View.INVISIBLE
                binding.mainView.visibility = View.VISIBLE
                val error = appState.error
                Snackbar.make(binding.root, error, Snackbar.LENGTH_LONG).show()
                Log.d("MyLog","Error")
            }
        }

    }


    private fun fillInAllFields(weather: Weather) {
        val savedWeather = Weather(localWeather.city,weather.temperature,weather.feelsLike,weather.condition)
        viewModel.saveWeather(savedWeather)

        with(binding) {
            cityName.text = localWeather.city.name
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()
            weatherCondition.text = weather.condition
            //coil
            imageView.load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")

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
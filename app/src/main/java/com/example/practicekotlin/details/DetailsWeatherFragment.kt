package com.example.practicekotlin.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.practicekotlin.databinding.DetailsFragmentBinding
import com.example.practicekotlin.domain.Weather
import com.example.practicekotlin.repository.WeatherDTO
import com.example.practicekotlin.repository.WeatherLoadListener
import com.example.practicekotlin.repository.WeatherLoader

class DetailsWeatherFragment : Fragment(), WeatherLoadListener {

    private lateinit var binding: DetailsFragmentBinding
    private val receiver:BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                val weatherDTO = it.getParcelableExtra<WeatherDTO>(RESULT_EXTRA)
                if (weatherDTO!=null){
                    fillInAllFields(weatherDTO)
                }else{
                    //выветси ошибку
                }
            }

        }

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
            // WeatherLoader(this, localWeather.city.lat, localWeather.city.lon).loadWeather()
        val intent = Intent(requireContext(),DetailsService::class.java)
        intent.putExtra(LAT_KEY, localWeather.city.lat)
        intent.putExtra(LON_KEY, localWeather.city.lon)
        requireContext().startService(intent)
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(receiver,
            IntentFilter(DETAILS_FILTER)
        )


    }

    private fun fillInAllFields(weatherDTO: WeatherDTO) {
        with(binding) {
            cityName.text = localWeather.city.name
            temperatureValue.text = weatherDTO.fact.temp.toString()
            feelsLikeValue.text = weatherDTO.fact.feels_like.toString()
            weatherCondition.text = weatherDTO.fact.condition
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

    override fun onLoaded(weatherDTO: WeatherDTO) {
        fillInAllFields(weatherDTO)
    }

    override fun onFiled(throwable: Throwable) {
        TODO("Not yet implemented")
    }


}
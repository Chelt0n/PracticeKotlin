package com.example.practicekotlin.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.practicekotlin.BuildConfig
import com.example.practicekotlin.databinding.DetailsFragmentBinding
import com.example.practicekotlin.domain.Weather
import com.example.practicekotlin.repository.WeatherDTO
import com.example.practicekotlin.repository.WeatherLoadListener
import com.example.practicekotlin.repository.WeatherLoader
import com.example.practicekotlin.utils.API_KEY_NAME
import com.example.practicekotlin.utils.API_URL
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.nio.file.attribute.AclEntry

class DetailsWeatherFragment : Fragment(), WeatherLoadListener {

    private lateinit var binding: DetailsFragmentBinding
    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                val weatherDTO = it.getParcelableExtra<WeatherDTO>(RESULT_EXTRA)
                if (weatherDTO != null) {
                    fillInAllFields(weatherDTO)
                } else {
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
//        WeatherLoader(this, localWeather.city.lat, localWeather.city.lon).loadWeather()
//        tryService()
        tryOkHttp3()

    }

    private fun tryService() {
        val intent = Intent(requireContext(), DetailsService::class.java)
        intent.putExtra(LAT_KEY, localWeather.city.lat)
        intent.putExtra(LON_KEY, localWeather.city.lon)
        requireContext().startService(intent)
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            receiver,
            IntentFilter(DETAILS_FILTER)
        )
    }

    private fun tryOkHttp3() {
        val client = OkHttpClient()
        val builder: Request.Builder = Request.Builder()
        builder.header(API_KEY_NAME, BuildConfig.WEATHER_API_KEY)
        builder.url(API_URL + "lat=${localWeather.city.lat}&lon=${localWeather.city.lon}")
        val request: Request = builder.build()
        val call: Call = client.newCall(request)
        //=======================Асинхронный запрос==================================
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                val serverResponse: String? = response.body?.string()
                if (response.isSuccessful && serverResponse != null) {
                    val weatherDTO =
                        Gson().fromJson<WeatherDTO>(serverResponse, WeatherDTO::class.java)
                    val handler = Handler(Looper.getMainLooper())
                    handler.post {
                        fillInAllFields(weatherDTO)
                    }
                }
            }

        })
        //==================Синхронный запрос===================
       /* Thread {
            val response: Response = call.execute()
            val serverResponse: String? = response.body?.string()
            if (response.isSuccessful && serverResponse != null) {
                val weatherDTO =
                    Gson().fromJson<WeatherDTO>(serverResponse, WeatherDTO::class.java)
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    fillInAllFields(weatherDTO)
                }
            }
        }.start()

        */


    }

     fun fillInAllFields(weatherDTO: WeatherDTO) {
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
    }


}
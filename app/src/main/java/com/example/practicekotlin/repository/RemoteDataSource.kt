package com.example.practicekotlin.repository

import android.os.Handler
import android.os.Looper
import com.example.practicekotlin.BuildConfig
import com.example.practicekotlin.details.DetailsWeatherFragment
import com.example.practicekotlin.utils.API_KEY_NAME
import com.example.practicekotlin.utils.API_URL
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException


class RemoteDataSource {
    fun getWeatherDetails(requestLink:String, callback: Callback){
        val client = OkHttpClient()
        val builder: Request.Builder = Request.Builder()
        builder.header(API_KEY_NAME, BuildConfig.WEATHER_API_KEY)
        builder.url(requestLink)
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
                        DetailsWeatherFragment().fillInAllFields(weatherDTO)
                    }
                }
            }

        })
    }
}
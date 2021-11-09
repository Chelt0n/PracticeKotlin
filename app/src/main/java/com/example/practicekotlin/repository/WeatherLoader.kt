package com.example.practicekotlin.repository


import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(
    private val listener: WeatherLoadListener,
    private val lat: Double,
    private val lon: Double
) {
    fun loadWeather() {
        val url = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
        Thread {
            val urlConnection = url.openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 10000
            urlConnection.addRequestProperty(
                "X-Yandex-API-Key",
                "face06ab-1adc-48e9-8d06-cf043aa4107a"
            )
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val weatherDTO = Gson().fromJson(reader, WeatherDTO::class.java)
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                listener.onLoaded(weatherDTO)
            }
            urlConnection.disconnect()
        }.start()
    }

}
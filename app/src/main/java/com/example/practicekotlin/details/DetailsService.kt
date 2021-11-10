package com.example.practicekotlin.details

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.practicekotlin.BuildConfig
import com.example.practicekotlin.repository.WeatherDTO
import com.example.practicekotlin.utils.API_KEY_NAME
import com.example.practicekotlin.utils.API_URL
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection
const val LAT_KEY = "LAT"
const val LON_KEY = "LON"
const val DETAILS_FILTER = "DETAILS_FILTER"
const val RESULT_EXTRA = "RESULT_EXTRA"
class DetailsService(name:String = "details"):IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val lat = it.getDoubleExtra(LAT_KEY, -1.0)
            val lon = it.getDoubleExtra(LON_KEY, -1.0)
            loadWeather(lat, lon)
        }


    }
    private fun loadWeather(lat:Double, lon:Double ){
        val url = URL(API_URL+"lat=${lat}&lon=${lon}")
        Thread {
            val urlConnection = url.openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 10000
            urlConnection.addRequestProperty(
                API_KEY_NAME,
                BuildConfig.WEATHER_API_KEY
            )
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val weatherDTO = Gson().fromJson(reader, WeatherDTO::class.java)
            val handler = Handler(Looper.getMainLooper())
            val sendIntent = Intent(DETAILS_FILTER)
            sendIntent.putExtra(RESULT_EXTRA,weatherDTO)
            LocalBroadcastManager.getInstance(this).sendBroadcast(sendIntent)



            urlConnection.disconnect()
        }.start()
    }
}
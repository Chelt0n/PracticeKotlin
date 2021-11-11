package com.example.practicekotlin.repository

import retrofit2.Callback


interface DetailsRepository {
    fun getWeatherDetailsFromServer(lat:Double, lon:Double, callback: Callback<WeatherDTO>)

}
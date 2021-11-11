package com.example.practicekotlin.repository


import com.example.practicekotlin.BuildConfig
import com.example.practicekotlin.utils.API_BASE_URL

import com.google.gson.GsonBuilder
import retrofit2.Callback

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSource {
    private val weatherApi by lazy {
        Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build().create(WeatherApi::class.java)
    }

    fun getWeatherDetails(lat: Double, lon: Double, callback: Callback<WeatherDTO>) {
        weatherApi.getWeather(BuildConfig.WEATHER_API_KEY, lat, lon).enqueue(callback)
    }
}
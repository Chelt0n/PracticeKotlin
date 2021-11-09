package com.example.practicekotlin.repository

interface WeatherLoadListener {
    fun onLoaded(weatherDTO: WeatherDTO)
    fun onFiled(throwable: Throwable)
}
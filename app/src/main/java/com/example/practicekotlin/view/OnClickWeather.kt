package com.example.practicekotlin.view

import com.example.practicekotlin.domain.Weather

interface OnClickWeather {
    fun onClick(weather: Weather)
}
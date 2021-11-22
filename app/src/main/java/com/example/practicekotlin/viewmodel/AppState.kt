package com.example.practicekotlin.viewmodel

import com.example.practicekotlin.domain.Weather

sealed class AppState{
    object Loading : AppState()
    data class SuccessMain(val weatherData: List<Weather>): AppState()
    data class SuccessDetails(val weatherData: Weather): AppState()
    data class Error(val error:String): AppState()

}

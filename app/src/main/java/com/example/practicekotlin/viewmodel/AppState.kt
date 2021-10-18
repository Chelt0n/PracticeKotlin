package com.example.practicekotlin.viewmodel

import com.example.practicekotlin.domain.Weather

sealed class AppState{
    object Loading : AppState()
    data class Success(val weatherData:Weather): AppState()
    data class Error(val error:Throwable): AppState()

}

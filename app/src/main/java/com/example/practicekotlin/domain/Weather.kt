package com.example.practicekotlin.domain

data class Weather(
    val city: City = defCity(),
    val temperature:Int = -1,
    val feelsLike:Int = -5
)

private fun defCity() = City("Москва", 55.0, 37.0)

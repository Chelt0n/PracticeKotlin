package com.example.practicekotlin.utils

import com.example.practicekotlin.domain.Weather
import com.example.practicekotlin.domain.defCity
import com.example.practicekotlin.repository.WeatherDTO

fun convertDtoToModel(weatherDTO: WeatherDTO):List<Weather>{
    return listOf(Weather(defCity(),weatherDTO.fact.temp,weatherDTO.fact.feels_like,weatherDTO.fact.condition))
}
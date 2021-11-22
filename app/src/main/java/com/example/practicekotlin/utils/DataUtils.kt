package com.example.practicekotlin.utils

import com.example.practicekotlin.domain.City
import com.example.practicekotlin.domain.Weather
import com.example.practicekotlin.domain.defCity
import com.example.practicekotlin.repository.WeatherDTO
import com.example.practicekotlin.ztest.lesson8.room.HistoryEntity

fun convertDtoToModel(weatherDTO: WeatherDTO): Weather {
    return Weather(
        defCity(),
        weatherDTO.fact.temp,
        weatherDTO.fact.feels_like,
        weatherDTO.fact.condition,
        weatherDTO.fact.icon
    )
}

fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> {
    return entityList.map {
        Weather(City(it.name, 0.0, 0.0), it.temperature, 0, it.condition)
    }
}

fun convertWeatherToHistoryEntity(weather: Weather): HistoryEntity {
    return HistoryEntity(0, weather.city.name, weather.temperature, weather.condition)
}
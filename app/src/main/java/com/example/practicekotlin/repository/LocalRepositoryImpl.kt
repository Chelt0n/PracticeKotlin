package com.example.practicekotlin.repository

import com.example.practicekotlin.domain.Weather
import com.example.practicekotlin.utils.convertHistoryEntityToWeather
import com.example.practicekotlin.utils.convertWeatherToHistoryEntity
import com.example.practicekotlin.ztest.lesson8.room.HistoryDAO

class LocalRepositoryImpl(private val localDataSource: HistoryDAO) : LocalRepository {
    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        localDataSource.insert(convertWeatherToHistoryEntity(weather))

    }
}
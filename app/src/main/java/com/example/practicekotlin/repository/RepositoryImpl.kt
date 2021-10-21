package com.example.practicekotlin.repository

import com.example.practicekotlin.domain.Weather
import com.example.practicekotlin.domain.getRussianCities
import com.example.practicekotlin.domain.getWorldCities

class RepositoryImpl : Repository {
    override fun getDataFromRemoteSource(): Weather {
        return Weather()
    }

    override fun getDataFromLocalSource(): Weather {
        return Weather()
    }

    override fun getWorldCitiesFromLocalSource(): List<Weather> = getWorldCities()

    override fun getRusCitiesFromLocalSource(): List<Weather> = getRussianCities()
}
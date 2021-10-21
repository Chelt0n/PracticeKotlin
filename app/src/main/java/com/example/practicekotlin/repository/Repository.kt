package com.example.practicekotlin.repository

import com.example.practicekotlin.domain.Weather

interface Repository {
    fun getDataFromRemoteSource():Weather
    fun getDataFromLocalSource():Weather

    fun getWorldCitiesFromLocalSource():List<Weather>
    fun getRusCitiesFromLocalSource():List<Weather>
}
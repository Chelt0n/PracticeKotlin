package com.example.practicekotlin.repository

import com.example.practicekotlin.domain.Weather

interface LocalRepository {
    fun getAllHistory():List<Weather>
    fun saveEntity(weather: Weather)
}
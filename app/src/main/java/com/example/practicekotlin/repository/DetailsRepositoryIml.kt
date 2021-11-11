package com.example.practicekotlin.repository

import retrofit2.Callback


class DetailsRepositoryIml(private val remoteDataSource: RemoteDataSource) : DetailsRepository {
    override fun getWeatherDetailsFromServer(lat: Double, lon: Double, callback: Callback<WeatherDTO>) {
        remoteDataSource.getWeatherDetails(lat, lon, callback)

    }
}
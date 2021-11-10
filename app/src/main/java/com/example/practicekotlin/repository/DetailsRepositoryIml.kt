package com.example.practicekotlin.repository

import okhttp3.Callback

class DetailsRepositoryIml(private val remoteDataSource: RemoteDataSource):DetailsRepository {
    override fun getWeatherDetailsFromServer(requestLink: String, callback: Callback) {
        remoteDataSource.getWeatherDetails(requestLink,callback)

    }
}
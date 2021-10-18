package com.example.practicekotlin.repository

import com.example.practicekotlin.domain.Weather

class RepositoryImpl:Repository {
    override fun getDataFromRemoteSource(): Weather {
        return Weather()
    }

    override fun getDataFromLocalSource(): Weather {
        return Weather()
    }
}
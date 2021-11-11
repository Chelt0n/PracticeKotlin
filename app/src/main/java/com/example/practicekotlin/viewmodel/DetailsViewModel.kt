package com.example.practicekotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicekotlin.repository.DetailsRepositoryIml
import com.example.practicekotlin.repository.RemoteDataSource
import com.example.practicekotlin.repository.WeatherDTO
import com.example.practicekotlin.utils.convertDtoToModel
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class DetailsViewModel(
    private val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private var detailsRepositoryIml: DetailsRepositoryIml = DetailsRepositoryIml(RemoteDataSource())
) : ViewModel() {

    fun getLiveData() = detailsLiveData
    fun getWeatherFromRemoteSource(requestLink: String) {
        detailsLiveData.value = AppState.Loading
        detailsRepositoryIml.getWeatherDetailsFromServer(requestLink, callback)
    }

    private val callback = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
        }

        override fun onResponse(call: Call, response: Response) {
            val serverResponse: String? = response.body?.string()
            if (response.isSuccessful && serverResponse != null) {
                val weatherDTO = Gson().fromJson(serverResponse, WeatherDTO::class.java)
                detailsLiveData.postValue(AppState.Success(convertDtoToModel(weatherDTO)))
            }
        }
    }

}
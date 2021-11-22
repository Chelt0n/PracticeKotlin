package com.example.practicekotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicekotlin.domain.Weather
import com.example.practicekotlin.repository.DetailsRepositoryIml
import com.example.practicekotlin.repository.LocalRepositoryImpl
import com.example.practicekotlin.repository.RemoteDataSource
import com.example.practicekotlin.repository.WeatherDTO
import com.example.practicekotlin.utils.convertDtoToModel
import com.example.practicekotlin.ztest.lesson8.MyApp.Companion.getHistoryDAO


import retrofit2.Callback


class DetailsViewModel(
    private val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private var detailsRepositoryIml: DetailsRepositoryIml = DetailsRepositoryIml(RemoteDataSource()),
    private var historyRepositoryImpl: LocalRepositoryImpl = LocalRepositoryImpl(getHistoryDAO())
) : ViewModel() {

    fun saveWeather(weather: Weather) {
        historyRepositoryImpl.saveEntity(weather)
    }

    fun getLiveData() = detailsLiveData
    fun getWeatherFromRemoteSource(lat: Double, lon: Double) {
        detailsLiveData.value = AppState.Loading
        detailsRepositoryIml.getWeatherDetailsFromServer(lat, lon, callback)


    }

    private val callback = object : Callback<WeatherDTO> {
        override fun onFailure(call: retrofit2.Call<WeatherDTO>, t: Throwable) {
            detailsLiveData.postValue(AppState.Error("Ошибка запроса"))
        }

        override fun onResponse(
            call: retrofit2.Call<WeatherDTO>,
            response: retrofit2.Response<WeatherDTO>
        ) {

            if (response.isSuccessful && response.body() != null) {
                val weatherDTO = response.body()
                weatherDTO?.let {
                    detailsLiveData.postValue(AppState.SuccessDetails(convertDtoToModel(weatherDTO)))
                }


            }
        }
    }
}
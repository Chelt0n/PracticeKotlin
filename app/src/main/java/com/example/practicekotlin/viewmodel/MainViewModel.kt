package com.example.practicekotlin.viewmodel

import android.os.SystemClock.sleep
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicekotlin.repository.RepositoryImpl

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private var repository: RepositoryImpl = RepositoryImpl()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getRusCitiesFromLocal() = getDataFromLocalSource(true)
    fun getWorldCitiesFromLocal() = getDataFromLocalSource(false)


    fun getDataFromLocalSource(isRussian: Boolean) {
        liveData.postValue(AppState.Loading)
        if (isRussian) {
            liveData.postValue(AppState.Success(repository.getRusCitiesFromLocalSource()))
        } else {
            liveData.postValue(AppState.Success(repository.getWorldCitiesFromLocalSource()))
        }

    }

}
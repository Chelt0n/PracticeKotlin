package com.example.practicekotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicekotlin.repository.LocalRepositoryImpl
import com.example.practicekotlin.ztest.lesson8.MyApp

class HistoryViewModel(
    private val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private var localRepositoryIml: LocalRepositoryImpl = LocalRepositoryImpl(MyApp.getHistoryDAO())
) : ViewModel() {

    fun getAllHistory() {
        historyLiveData.value = AppState.Loading
        Thread {
            historyLiveData.postValue(AppState.SuccessMain(localRepositoryIml.getAllHistory()))
        }.start()

    }

    fun getLiveData() = historyLiveData

}
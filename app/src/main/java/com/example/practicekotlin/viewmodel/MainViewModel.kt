package com.example.practicekotlin.viewmodel

import android.os.SystemClock.sleep
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicekotlin.repository.RepositoryImpl

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private var repository:RepositoryImpl = RepositoryImpl()
) : ViewModel() {

    fun getLiveData() = liveData
    fun getDataFromRemoteSource(){
        liveData.postValue(AppState.Loading)
        val random = (0..10).random()
        Thread{
            sleep(2000)
            if (random<5){
                liveData.postValue(AppState.Error(error = Throwable("Ошибка подключения")))
            }else{
                liveData.postValue(AppState.Success(repository.getDataFromRemoteSource()))
            }
        }.start()
    }

}
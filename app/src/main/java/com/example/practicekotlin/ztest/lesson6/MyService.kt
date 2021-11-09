package com.example.practicekotlin.ztest.lesson6

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyService(name:String = "name"):IntentService(name) {


    override fun onCreate() {
        createLogMessage("Create")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createLogMessage("onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        createLogMessage("onDestroy")
        super.onDestroy()
    }

    override fun onHandleIntent(intent: Intent?) {
        createLogMessage("onHandleIntent + ${intent?.getStringExtra("KEY1")}")

    }
    private fun createLogMessage(message:String){
        Log.d("myLog",message)
    }
}
package com.example.practicekotlin.ztest.lesson6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val text = intent?.action
        Log.d("myLog", text.toString())
        Toast.makeText(context!!, text, Toast.LENGTH_LONG).show()
    }
}
package com.example.practicekotlin.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.practicekotlin.R
import com.example.practicekotlin.ztest.lesson4.Test
import com.example.practicekotlin.ztest.lesson6.MyBroadcastReceiver
import com.example.practicekotlin.ztest.lesson6.ThreadsFragment

class MainActivity : AppCompatActivity() {

    private val receiver = MyBroadcastReceiver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction().add(R.id.container, ListOfCitiesFragment.newInstance())
                .commit()

        registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.test_lesson_six_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_open_fragment_threads -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ThreadsFragment.newInstance()).commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
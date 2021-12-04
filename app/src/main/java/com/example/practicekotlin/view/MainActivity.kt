package com.example.practicekotlin.view

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.practicekotlin.R
import com.example.practicekotlin.history.HistoryListOfCitiesFragment
import com.example.practicekotlin.ztest.lesson6.MyBroadcastReceiver
import com.example.practicekotlin.ztest.lesson6.ThreadsFragment
import com.example.practicekotlin.ztest.lesson9.ContentProviderFragment

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
            R.id.action_open_fragment_history -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HistoryListOfCitiesFragment.newInstance())
                    .addToBackStack("")
                    .commit()
                true
            }
            R.id.action_open_fragment_content_provider -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ContentProviderFragment.newInstance())
                    .addToBackStack("")
                    .commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
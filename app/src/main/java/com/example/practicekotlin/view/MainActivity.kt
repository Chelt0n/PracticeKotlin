package com.example.practicekotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicekotlin.R
import com.example.practicekotlin.ztest.lesson3.Test

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var test = Test().startAllFunction()

        if (savedInstanceState==null)
            supportFragmentManager
                .beginTransaction().replace(R.id.container, ListOfCitiesFragment.newInstance())
                .commit()
    }

}
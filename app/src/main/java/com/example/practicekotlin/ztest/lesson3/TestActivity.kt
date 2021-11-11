package com.example.practicekotlin.ztest.lesson3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practicekotlin.databinding.TestLessonThreeBinding
import com.example.practicekotlin.domain.getRussianCities

class TestActivity : AppCompatActivity() {
    private val adapter = AdapterTest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: TestLessonThreeBinding = TestLessonThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerViewTest.adapter = adapter
        adapter.getData(getRussianCities())

    }
}
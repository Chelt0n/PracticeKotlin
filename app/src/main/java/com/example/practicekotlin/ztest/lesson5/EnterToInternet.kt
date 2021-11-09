package com.example.practicekotlin.ztest.lesson5

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.practicekotlin.databinding.TestLessonFiveBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class EnterToInternet  : AppCompatActivity() {
    lateinit var binding: TestLessonFiveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = TestLessonFiveBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.ok.setOnClickListener {
            showUrl(binding.editText.text.toString())

        }
    }
    private fun showUrl(urlString:String){
        val url = URL(urlString)
        Thread{
            val urlConnection = url.openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 10000
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val result = getLines(reader)
            runOnUiThread {
                binding.webView.loadDataWithBaseURL(null,result, "text/html; charset=utf-8", "utf-8",null)
            }
            urlConnection.disconnect()
        }.start()



    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun getLines(reader:BufferedReader):String{
        return reader.lines().collect(Collectors.joining("\n"))
    }


}
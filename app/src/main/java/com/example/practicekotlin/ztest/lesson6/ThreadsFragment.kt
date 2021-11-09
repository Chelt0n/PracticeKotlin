package com.example.practicekotlin.ztest.lesson6

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.practicekotlin.databinding.TestLessonSixBinding
import java.util.*
import kotlin.time.minutes

class ThreadsFragment : Fragment() {

    private var _binding: TestLessonSixBinding? = null
    private val binding: TestLessonSixBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TestLessonSixBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tryThreads()
        tryService()



    }

    private fun tryService() {
        binding.service.setOnClickListener {
            val intent = Intent(context, MyService::class.java)
            intent.putExtra("KEY1", "hi service")
            requireActivity().startService(intent)
        }
    }

    private fun tryThreads() {
        binding.buttonThread.setOnClickListener {
            val timer = binding.editTextThread.text.toString().toInt()
            startCalculation(timer)
            binding.mainContainer.addView(TextView(it.context).apply {
                text = "Thread"
                textSize = 40f
            })

        }
        binding.thread.setOnClickListener {
            Thread {
                val timer = binding.editTextThread.text.toString().toInt()
                val handler = Handler(Looper.getMainLooper())
                startCalculation(timer)


                handler.post {
                    binding.mainContainer.addView(TextView(it.context).apply {
                        text = "Thread"
                        textSize = 40f
                    })
                }


            }.start()
        }

        val handlerThread = MyThread()
        handlerThread.start()
        val myHandler1 = Handler(Looper.getMainLooper())

        binding.handlerThread.setOnClickListener {
            val handler = handlerThread.mHandler
            for (i in 1..200) {


                handler?.post {
                    val timer = binding.editTextThread.text.toString().toInt()
                    startCalculation(timer)

                    myHandler1.post {
                        binding.textViewThread.text = i.toString()
                        /*  binding.mainContainer.addView(TextView(it.context).apply {
                              text = "Handler Thread"
                              textSize = 40f
                          })
                        */
                    }


                }
            }
        }
    }

    private fun startCalculation(timer: Int) {
        val currentTime = Date().time
        while (currentTime + timer * 1000 >= Date().time) {

        }
    }

    companion object {
        fun newInstance() = ThreadsFragment()
    }

}

class MyThread : Thread() {
    var mHandler: Handler? = null
    override fun run() {
        Looper.prepare()
        mHandler = Handler(Looper.myLooper()!!)
        Looper.loop()
    }
}

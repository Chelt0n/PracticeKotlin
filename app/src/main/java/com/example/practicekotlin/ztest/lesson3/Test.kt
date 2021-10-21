package com.example.practicekotlin.ztest.lesson3

import android.util.Log

class Test {
    fun startAllFunction() {
        arrays()
        extensionFunction()
        generic()

    }

    private fun arrays() {
        val num: List<Int> = listOf(1, 2, 3)
        val numMutable = num.toMutableList()
        numMutable.add(1)
        Log.d("MyLog", numMutable.toString())
        val animals: List<Animals> = listOf(Animals("барбос", "пес"), Animals("барбос", "пес"))
    }


    private fun extensionFunction() {
        fun Int.toMultiple(): Int = this * this
        var extensionMultiple = 3
        extensionMultiple.toMultiple()
        Log.d("MyLog", "${extensionMultiple.toMultiple()}")

    }

    private fun generic() {
        Log.d("MyLog", "generic\n")
        testGeneric("s")
        testGeneric(1)
        testGeneric(1.2)

    }

    private fun <T> testGeneric(input: T) {
        Log.d("MyLog", input.toString())
    }

}
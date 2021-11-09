package com.example.practicekotlin.ztest.lesson4

import android.util.Log

class Test {
    fun lambda() {
//        a|  переменная функционального типа
        val a = {
            Log.d("mylog", "1")
            "2"
        }

        val b = run {
            Log.d("mylog", "3")
            "4"
        }
        f1("1","2")
        f2("1","2")
        f3(f1)
        f3(f2)
        val people:List<Extension> = listOf(Extension("first",1),Extension("second",2))
        people.forEach { it.p1()}

    }

    val f1 = fun(string1:String,string2:String):String{

        return string1+string2
    }
    val f2 = {string1:String,string2:String ->
        val str = string1+string2
        str
    }
    private fun f3(fun1:(string1:String, string2:String) -> String) {
        Log.d("myLog", "f3")
    }

    fun Extension.p1(){
        Log.d("mylog", "${this.name},${this.age}")
    }
data class TestExtension(val name:String, val age:Int)
}
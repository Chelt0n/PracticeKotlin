package com.example.practicekotlin.ztest.lesson4

class Extension(val name: String, val age: Int) {
    fun main() {
        val people: List<Extension> = listOf(Extension("first", 1), Extension("second", 2))

        with(people) {
            get(1).name
            get(2).name
            get(1).age
            get(2).age
        }
        people.apply {
            get(1).name
            get(2).name
            get(1).age
            get(2).age
        }

    }

}

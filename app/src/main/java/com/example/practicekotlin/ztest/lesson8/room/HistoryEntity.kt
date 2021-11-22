package com.example.practicekotlin.ztest.lesson8.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.concurrent.locks.Condition

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val temperature: Int,
    val condition: String
)


/*
    apply plugin: 'kotlin-kapt'


    implementation 'androidx.room:room-runtime:2.3.0'
    kapt 'androidx.room:room-compiler:2.3.0'

 */

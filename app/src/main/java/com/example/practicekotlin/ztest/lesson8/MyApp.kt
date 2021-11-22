package com.example.practicekotlin.ztest.lesson8

import android.app.Application
import androidx.room.Room
import com.example.practicekotlin.ztest.lesson8.room.HistoryDAO
import com.example.practicekotlin.ztest.lesson8.room.HistoryDataBase
import com.example.practicekotlin.ztest.lesson8.room.HistoryEntity
import java.lang.IllegalStateException

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this

    }

    companion object {
        private var appInstance: MyApp? = null
        private var db: HistoryDataBase? = null
        private const val DB_NAME = "HistoryDataBase.db"

        fun getHistoryDAO(): HistoryDAO {
            if (db == null) {
                if (appInstance != null) {
                    db = Room.databaseBuilder(appInstance!!, HistoryDataBase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .build()
                } else {
                    throw IllegalStateException()
                }
            }
            return db!!.historyDAO()
        }
    }
}
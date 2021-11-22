package com.example.practicekotlin.ztest.lesson8.room

import androidx.room.*

@Dao
interface HistoryDAO {


    @Query("SELECT * FROM HistoryEntity")
    fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE name LIKE :name")
    fun getDataByName(name: String): List<HistoryEntity>

    @Delete
    fun delete(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)
}
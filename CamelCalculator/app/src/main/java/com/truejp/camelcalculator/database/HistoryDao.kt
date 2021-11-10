package com.truejp.camelcalculator.database

import androidx.room.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getAll(): List<History>

    @Query("SELECT * FROM history ORDER BY name ASC")
    fun getAllHistoriesSortedByName(): List<History>

    @Query("DELETE FROM history")
    suspend fun deleteAll()


    @Insert
    fun insertAll(history: List<History>)

    @Insert
    fun insert(history: History)

    @Delete
    fun delete(history: History)
}

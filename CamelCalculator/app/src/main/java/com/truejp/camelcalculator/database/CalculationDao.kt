package com.truejp.camelcalculator.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculationDao {
    @Query("SELECT * FROM calculation_table ORDER BY camels DESC")
    fun getAllCalculationsSortedByName(): LiveData<List<Calculation>>

    @Insert
    suspend fun insert(calculation: Calculation)

    @Query("DELETE FROM calculation_table")
    suspend fun deleteAll()

    @Delete
    fun delete(calculation: Calculation)
}
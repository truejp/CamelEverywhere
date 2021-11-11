package com.truejp.camelcalculator.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.jar.Attributes

@Dao
interface CalculationDao {
    @Query("SELECT * FROM calculation_table ORDER BY user ASC")
    fun getAllCalculationsSortedByName(): LiveData<List<Calculation>>

    @Insert
    suspend fun insert(calculation: Calculation)

    @Query("DELETE FROM calculation_table")
    suspend fun deleteAll()
}
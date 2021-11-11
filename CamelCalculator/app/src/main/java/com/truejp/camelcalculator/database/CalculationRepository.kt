package com.truejp.camelcalculator.database

import androidx.lifecycle.LiveData
import java.util.jar.Attributes

class CalculationRepository(private val calculationDao: CalculationDao) {
    val allCalculations: LiveData<List<Calculation>> =
        calculationDao.getAllCalculationsSortedByName()

    suspend fun insert(calculation: Calculation) {
        calculationDao.insert(calculation)
    }

    suspend fun deleteAll() {
        calculationDao.deleteAll()
    }
}
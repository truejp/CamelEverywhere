package com.truejp.camelcalculator.database

import androidx.lifecycle.LiveData

class HistoryRepository(private val historyDao: HistoryDao) {
    val allHistories: List<History> = historyDao.getAllHistoriesSortedByName()
    suspend fun insert(history: History) {
        historyDao.insert(history)
    }
    suspend fun deleteAll() {
        historyDao.deleteAll()
    }
}
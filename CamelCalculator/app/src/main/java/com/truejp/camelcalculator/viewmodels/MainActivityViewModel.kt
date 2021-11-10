package com.truejp.camelcalculator.viewmodels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truejp.camelcalculator.database.History
import com.truejp.camelcalculator.database.HistoryRepository
import kotlinx.coroutines.launch
var testText = ""

class MainActivityViewModel (
    private val repository: HistoryRepository
) : ViewModel() {
    fun insert() = viewModelScope.launch {
        repository.insert(History(0, testText))
    }
}
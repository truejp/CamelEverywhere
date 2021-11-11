package com.truejp.camelcalculator.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truejp.camelcalculator.database.Calculation
import com.truejp.camelcalculator.database.CalculationRepository
import kotlinx.coroutines.launch

class MainActivityViewModel (
    private val repository: CalculationRepository
) : ViewModel() {
    val notes = repository.allCalculations
    var userText = ""

    fun insert() = viewModelScope.launch {
        repository.insert(Calculation(0, userText))
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

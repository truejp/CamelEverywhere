package com.truejp.camelcalculator.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truejp.camelcalculator.database.Calculation
import com.truejp.camelcalculator.database.CalculationRepository
import kotlinx.coroutines.launch

class MainActivityViewModel (
    private val repository: CalculationRepository
) : ViewModel() {
    val calculations = repository.allCalculations
    var userText = ""
    var gender = false
    var age = 0
    var weigth = 0
    var body = 0
    var eyecolor = 0
    var haircolor = 0
    var breastsize = 0
    var camels = 0

    fun insert() = viewModelScope.launch {
        repository.insert(Calculation(0, userText, gender, age, weigth, body, eyecolor, haircolor, breastsize, camels))
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
    fun delete() = viewModelScope.launch {
        repository.delete()
    }
}

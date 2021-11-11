package com.truejp.camelcalculator.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truejp.camelcalculator.database.Calculation
import com.truejp.camelcalculator.database.CalculationRepository
import kotlinx.coroutines.launch
import java.util.*

class MainActivityViewModel (
    private val repository: CalculationRepository
) : ViewModel() {
    val calculations = repository.allCalculations
    var userText = ""
    var gender = ""
    var age = ""
    var weigth = ""
    var body = ""
    var eyecolor = ""
    var haircolor = ""
    var breastsize = ""
    var camels = ""
    var comment = ""
    var date=""

    fun insert() = viewModelScope.launch {
        repository.insert(Calculation(0, userText, gender, age, weigth, body, eyecolor, haircolor, breastsize, camels, comment, date))
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
    fun delete(calculation: Calculation) = viewModelScope.launch {
        repository.delete(calculation)
    }
}

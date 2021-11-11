package com.truejp.camelcalculator.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.truejp.camelcalculator.database.CalculationRepository
import java.lang.IllegalArgumentException

class MainActivityViewModelFactory(
    private val calculationRepository: CalculationRepository
    ): ViewModelProvider.Factory {
    //Fragezeichen entfertnt bei T : Viewmodel?>
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                return MainActivityViewModel(calculationRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}

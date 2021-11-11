package com.truejp.camelcalculator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.truejp.camelcalculator.database.CalculationDatabase
import com.truejp.camelcalculator.database.CalculationRepository
import com.truejp.camelcalculator.databinding.FragmentResultBinding
import com.truejp.camelcalculator.model.MainActivityViewModel
import com.truejp.camelcalculator.model.MainActivityViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: FragmentResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.dhHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)


        //Access DB from MainActivity
        val database = CalculationDatabase.getInstance(this.application)
        val calculationRepository = CalculationRepository(database.calculationDao)
        val viewModelFactory =  MainActivityViewModelFactory(calculationRepository)
        val mainActivityViewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.dhHostFragment)
        return navController.navigateUp()
    }
}
package com.truejp.camelcalculator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.room.Room
import com.truejp.camelcalculator.database.*
import com.truejp.camelcalculator.databinding.ActivityMainBinding
import com.truejp.camelcalculator.viewmodels.MainActivityViewModel
import com.truejp.camelcalculator.viewmodels.MainActivityViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    //DB Test
    private lateinit var database: HistoryDatabase
    private lateinit var historyDao: HistoryDao
    private val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    private val historyA = History(1,"Peter" /*,true,20,50,3,2,1,45,"Nein", sdf.format(Date())*/)

    //Implement DataBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        database = Room.databaseBuilder(
            applicationContext,
            HistoryDatabase::class.java, "database-name"
        ).build()
        historyDao = database.historyDao()
        //historyDao.insertAll(listOf<History>(historyA, historyA)) --> läuft nur in einem Background Task
        //val results: List<History> = historyDao.getAll()
        //var i = Log.i("Found Something:", results.toString())

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set Databinding, get DB Data

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val database = HistoryDatabase.getInstance(application)
        val historyRepository: HistoryRepository = HistoryRepository(database.historyDao())
        val viewModelFactory = MainActivityViewModelFactory(historyRepository)
        val mainActivityViewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
        binding.viewModel = mainActivityViewModel


        val navController = this.findNavController(R.id.dhHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.dhHostFragment)
        return navController.navigateUp()
    }
}
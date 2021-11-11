package com.truejp.camelcalculator.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Calculation::class], version = 1, exportSchema = false)
abstract class CalculationDatabase: RoomDatabase() {
    abstract val calculationDao: CalculationDao

    companion object { @Volatile
    private var INSTANCE: CalculationDatabase? = null

        fun getInstance(context: Context): CalculationDatabase { synchronized(this) {
            var instance = INSTANCE
            if (instance == null) { instance = Room.databaseBuilder( context.applicationContext, CalculationDatabase::class.java, "calculation_database"
            ).fallbackToDestructiveMigration()
                .build() }
            return instance
        }
        }
    }
}
package com.truejp.camelcalculator.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [History::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object { @Volatile
    private var INSTANCE: HistoryDatabase? = null
        fun getInstance(context: Context): HistoryDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, HistoryDatabase::class.java, "note_database"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }

        }
    }
}

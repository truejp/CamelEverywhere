package com.truejp.camelcalculator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="calculation_table")
data class Calculation(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "user")
    val user: String
)
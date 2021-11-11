package com.truejp.camelcalculator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="calculation_table")
data class Calculation(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "user")
    val user: String,

    @ColumnInfo(name = "gender")
    val gender: Boolean,

    @ColumnInfo(name = "age")
    val age: Int,

    @ColumnInfo(name = "weight")
    val weight: Int,

    @ColumnInfo(name = "body")
    val body: Int,

    @ColumnInfo(name = "eyecolor")
    val eyecolor: Int,

    @ColumnInfo(name = "haircolor")
    val haircolor: Int,

    @ColumnInfo(name = "breastsize")
    val breastsize: Int,

    @ColumnInfo(name = "camels")
    val camels: Int
)
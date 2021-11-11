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
    val gender: String,

    @ColumnInfo(name = "age")
    val age: String,

    @ColumnInfo(name = "weight")
    val weight: String,

    @ColumnInfo(name = "body")
    val body: String,

    @ColumnInfo(name = "eyecolor")
    val eyecolor: String,

    @ColumnInfo(name = "haircolor")
    val haircolor: String,

    @ColumnInfo(name = "breastsize")
    val breastsize: String,

    @ColumnInfo(name = "camels")
    val camels: String,

    @ColumnInfo(name = "comment")
    val comment: String
)
package com.truejp.camelcalculator.database

import androidx.room.*

@Entity(tableName = "history")
data class History(@PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") val historyID: Int, val name: String/*, val gender: Boolean, val age: Int, val weight:Int, val body: Int, val eye: Int, val hair: Int, val result: Int, val comment: String, val date: String*/) {
    override fun toString() = name
}

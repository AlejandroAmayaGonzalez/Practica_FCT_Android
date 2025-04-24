package com.aamagon.practica_fct_android.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bills_table")
data class BillEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "quantity") val quantity: Double,
    @ColumnInfo(name = "date") val date: String
)
package com.aamagon.practica_fct_android.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aamagon.practica_fct_android.data.database.dao.BillDao
import com.aamagon.practica_fct_android.data.database.entities.BillEntity

@Database(entities = [BillEntity::class], version = 1, )
abstract class BillDatabase: RoomDatabase() {

    abstract fun getBillDao(): BillDao
}
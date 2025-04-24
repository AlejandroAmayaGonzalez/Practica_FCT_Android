package com.aamagon.practica_fct_android.data.database

import android.content.Context
import androidx.room.Room

class DatabaseProvider {

    companion object{
        private const val DATABASE_NAME = "BillDB"
        private lateinit var database: BillDatabase

        fun getDataBase(context: Context): BillDatabase{
            database = Room.databaseBuilder(
                context.applicationContext,
                BillDatabase::class.java,
                DATABASE_NAME
            ).build()

            return database
        }
    }
}
package com.aamagon.practica_fct_android.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aamagon.practica_fct_android.data.database.entities.BillEntity

@Dao
interface BillDao {

    @Query("SELECT * FROM bills_table")
    suspend fun getAllBills(): List<BillEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(bills: List<BillEntity>)

    @Query("DELETE FROM bills_table")
    suspend fun deleteBills()
}
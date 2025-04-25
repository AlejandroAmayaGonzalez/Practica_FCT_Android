package com.aamagon.practica_fct_android.data

import com.aamagon.practica_fct_android.data.database.dao.BillDao
import com.aamagon.practica_fct_android.data.model.BillsListModel
import com.aamagon.practica_fct_android.data.network.ApiService
import javax.inject.Inject

class BillsRepository @Inject constructor(
    private val api: ApiService,
    private val billDao: BillDao
) {

    lateinit var billsList: BillsListModel

    suspend fun getAllBills(): BillsListModel{
        val response = api.getBills()
        billsList = response
        return response
    }
}
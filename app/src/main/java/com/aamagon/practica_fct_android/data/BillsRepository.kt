package com.aamagon.practica_fct_android.data

import com.aamagon.practica_fct_android.data.model.BillsListModel
import com.aamagon.practica_fct_android.data.network.ApiService

class BillsRepository {

    private val api = ApiService()

    lateinit var billsList: BillsListModel

    suspend fun getAllBills(): BillsListModel{
        val response = api.getBills()
        billsList = response
        return response
    }
}
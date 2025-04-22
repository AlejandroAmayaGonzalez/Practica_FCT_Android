package com.aamagon.practica_fct_android.data.network

import com.aamagon.practica_fct_android.core.RetroFitHelper
import com.aamagon.practica_fct_android.data.model.BillsListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiService {

    private val retrofit = RetroFitHelper.getRetrofit()

    suspend fun getBills(): BillsListModel{
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getBills()
            response.body() ?: BillsListModel(0, emptyList())
        }
    }
}
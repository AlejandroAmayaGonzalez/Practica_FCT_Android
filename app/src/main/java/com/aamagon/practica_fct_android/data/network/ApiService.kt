package com.aamagon.practica_fct_android.data.network

import com.aamagon.practica_fct_android.data.model.BillsListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor(
    private val api: ApiClient
) {

    suspend fun getBills(): BillsListModel{
        return withContext(Dispatchers.IO) {
            val response = api.getBills()
            response.body() ?: BillsListModel(0, emptyList())
        }
    }
}
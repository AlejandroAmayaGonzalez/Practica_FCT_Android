package com.aamagon.practica_fct_android.data.network.bills

import com.aamagon.practica_fct_android.core.di.BillService
import com.aamagon.practica_fct_android.data.model.BillsListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BillsApiService @Inject constructor(
    @BillService private val api: BillsApiClient
) {

    suspend fun getBills(): BillsListModel{
        return withContext(Dispatchers.IO) {
            val response = api.getBills()
            response.body() ?: BillsListModel(0, emptyList())
        }
    }
}
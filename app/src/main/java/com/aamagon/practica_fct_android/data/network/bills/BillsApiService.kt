package com.aamagon.practica_fct_android.data.network.bills

import android.util.Log
import com.aamagon.practica_fct_android.core.di.BillService
import com.aamagon.practica_fct_android.data.model.BillsListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import javax.inject.Inject

class BillsApiService @Inject constructor(
    @BillService private val api: BillsApiClient
) {

    suspend fun getBills(): BillsListModel{
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getBills()
                response.body() ?: BillsListModel(0, emptyList())
            }catch (_: SocketTimeoutException){
                Log.e("excepcion", "se ha producido socketException")
                BillsListModel(0, emptyList())
            }
        }
    }
}
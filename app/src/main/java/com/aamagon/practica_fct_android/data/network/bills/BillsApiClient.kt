package com.aamagon.practica_fct_android.data.network.bills

import com.aamagon.practica_fct_android.data.model.BillsListModel
import retrofit2.Response
import retrofit2.http.GET

interface BillsApiClient {
    @GET(".")
    suspend fun getBills(): Response<BillsListModel>
}
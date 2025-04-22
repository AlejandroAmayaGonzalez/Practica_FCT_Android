package com.aamagon.practica_fct_android.data.network

import com.aamagon.practica_fct_android.data.model.BillsListModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @GET("facturas")
    suspend fun getBills(): Response<BillsListModel>
}
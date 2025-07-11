package com.aamagon.practica_fct_android.data.network.bills

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.aamagon.practica_fct_android.data.model.BillsListModel
import retrofit2.Response
import retrofit2.http.GET

interface BillsApiClient {
    @Mock
    @GET(".")
    @MockResponse(body = "facturas.json")
    suspend fun getBills(): Response<BillsListModel>
}
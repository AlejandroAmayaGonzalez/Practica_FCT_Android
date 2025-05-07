package com.aamagon.practica_fct_android.data.network.detail

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.aamagon.practica_fct_android.data.model.DetailModel
import retrofit2.Response
import retrofit2.http.GET

interface DetailApiClient {
    @Mock
    @GET(".")
    @MockResponse(body = "detalle.json")
    suspend fun getDetail(): Response<DetailModel>
}
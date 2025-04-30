package com.aamagon.practica_fct_android.data.network.detail

import com.aamagon.practica_fct_android.core.di.DetailService
import com.aamagon.practica_fct_android.data.model.DetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailApiService @Inject constructor(
    @DetailService private val api: DetailApiClient
){

    suspend fun getDetail(): DetailModel{
        return withContext(Dispatchers.IO) {
            val response = api.getDetail()
            response.body() ?: DetailModel("","","","","")
        }
    }
}
package com.aamagon.practica_fct_android.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/* Possible URLs
 * https://viewnextandroid.mocklab.io/
 * https://viewnextandroid.wiremockapi.cloud/
 */

object RetroFitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://d869b751-7eb6-44b9-8db5-33f51b4f1d7a.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

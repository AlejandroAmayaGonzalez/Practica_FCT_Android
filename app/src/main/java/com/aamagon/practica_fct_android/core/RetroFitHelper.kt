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
            .baseUrl("https://viewnextandroid.mocklab.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

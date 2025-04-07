package com.aamagon.practica_fct_android.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://viewnextandroid.mocklab.io/facturas")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

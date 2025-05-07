package com.aamagon.practica_fct_android.core.di

import com.aamagon.practica_fct_android.data.network.bills.BillsApiClient
import com.aamagon.practica_fct_android.data.network.detail.DetailApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    /* URLs:
     * https://viewnextandroid.mocklab.io/facturas
     * https://viewnextandroid.wiremockapi.cloud/facturas
     */

    @BillService
    @Singleton
    @Provides
    fun provideBillsRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://d869b751-7eb6-44b9-8db5-33f51b4f1d7a.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @DetailService
    @Singleton
    @Provides
    fun provideDetailRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://008069f0-dca1-4dfa-b304-225ce4dfb044.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /*
    @BillService
    @Singleton
    @Provides
    fun provideBillsApiClient(@BillService retrofit: Retrofit): BillsApiClient{
        return retrofit.create(BillsApiClient::class.java)
    }

    @DetailService
    @Singleton
    @Provides
    fun provideDetailApiClient(@DetailService retrofit: Retrofit): DetailApiClient{
        return retrofit.create(DetailApiClient::class.java)
    }*/
}
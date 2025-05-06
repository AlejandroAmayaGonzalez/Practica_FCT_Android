package com.aamagon.practica_fct_android.core.di

import co.infinum.retromock.Retromock
import com.aamagon.practica_fct_android.data.network.bills.BillsApiClient
import com.aamagon.practica_fct_android.data.network.detail.DetailApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetromockModule {

    @BillService
    @Singleton
    @Provides
    fun provideBillsRetromock(@BillService retrofit: Retrofit): Retromock{
        return Retromock.Builder()
            .retrofit(retrofit)
            .build()
    }

    @DetailService
    @Singleton
    @Provides
    fun provideDetailRetromock(@DetailService retrofit: Retrofit): Retromock{
        return Retromock.Builder()
            .retrofit(retrofit)
            .build()
    }

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
    }
}
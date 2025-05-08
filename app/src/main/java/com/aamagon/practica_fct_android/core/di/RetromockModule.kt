package com.aamagon.practica_fct_android.core.di

import android.content.Context
import co.infinum.retromock.Retromock
import com.aamagon.practica_fct_android.core.bodyFactory.AssetsBodyFactory
import com.aamagon.practica_fct_android.data.network.detail.DetailApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetromockModule {

    @BillService
    @Singleton
    @Provides
    fun provideBillsRetromock(
        @BillService retrofit: Retrofit,
        @ApplicationContext context: Context
    ): Retromock{
        return Retromock.Builder()
            .retrofit(retrofit)
            .defaultBodyFactory(AssetsBodyFactory(context.assets::open))
            .build()
    }

    @DetailService
    @Singleton
    @Provides
    fun provideDetailRetromock(
        @DetailService retrofit: Retrofit,
        @ApplicationContext context: Context
    ): Retromock{
        return Retromock.Builder()
            .retrofit(retrofit)
            .defaultBodyFactory(AssetsBodyFactory(context.assets::open))
            .build()
    }

    /*
    @BillService
    @Singleton
    @Provides
    fun provideBillsApiClient(@BillService retromock: Retromock): BillsApiClient{
        return retromock.create(BillsApiClient::class.java)
    }*/

    @DetailService
    @Singleton
    @Provides
    fun provideDetailApiClient(@DetailService retromock: Retromock): DetailApiClient{
        return retromock.create(DetailApiClient::class.java)
    }
}
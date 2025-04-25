package com.aamagon.practica_fct_android.core.di

import android.content.Context
import androidx.room.Room
import com.aamagon.practica_fct_android.data.database.BillDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "BillDB"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BillDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideBillDao(db: BillDatabase) = db.getBillDao()
}
package com.aamagon.practica_fct_android.core.di

import android.content.Context
import android.content.SharedPreferences
import com.aamagon.practica_fct_android.data.model.MockButtonPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences{
        return context.getSharedPreferences("use_mock", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideMockButtonPreference(
        sharedPreferences: SharedPreferences
    ): MockButtonPreference{
        return MockButtonPreference(sharedPreferences)
    }
}
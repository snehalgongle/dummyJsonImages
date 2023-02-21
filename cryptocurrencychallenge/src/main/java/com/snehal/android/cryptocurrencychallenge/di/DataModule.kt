package com.snehal.android.cryptocurrencychallenge.di

import com.snehal.android.cryptocurrencychallenge.data.CoincapService
import com.snehal.android.cryptocurrencychallenge.data.PHOTOS_ENDPOINT_HOST
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideCoincapService(): CoincapService {
        return Retrofit.Builder()
            .baseUrl(PHOTOS_ENDPOINT_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoincapService::class.java)
    }
}
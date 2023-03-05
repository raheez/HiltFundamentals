package com.example.hiltfundamentals.di

import com.example.hiltfundamentals.domain.CryptoCurrencyImpl
import com.example.hiltfundamentals.domain.CryptoCurrencyRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideCryptocurrencyRepository(): CryptoCurrencyRepo = CryptoCurrencyImpl()
}
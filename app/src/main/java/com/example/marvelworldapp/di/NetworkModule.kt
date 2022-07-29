package com.example.marvelworldapp.di

import com.example.marvelworldapp.data.network.ApiClient
import com.example.marvelworldapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module // especificamos que es un modulo
@InstallIn(SingletonComponent::class) // alcance a nivel de aplicación
object NetworkModule {

    // proveer retrofit
    @Singleton // patrón singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }
}
package com.vtlsh.universerickmorty.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vtlsh.universerickmorty.data.remote.ApiService
import com.vtlsh.universerickmorty.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule { //remote settings

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideOkhttpClientBuilder(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @Singleton
    @Provides
    fun provideApiService(gson: Gson, client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ApiService::class.java)
    }

}
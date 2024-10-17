package com.example.tp4.di

import com.example.tp4.model.post.retrofit.ApiService
import com.example.tp4.model.post.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return RetrofitClient.instance
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}

package com.example.dimvvmflow_recall.di

import com.example.dimvvmflow_recall.TodoAdapter
import com.example.dimvvmflow_recall.network.ApiData
import com.example.dimvvmflow_recall.network.ApiData.Companion.BASEURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance() = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApiData(retrofit: Retrofit): ApiData {
        return retrofit.create(ApiData::class.java)
    }

    @Singleton
    @Provides
    fun provideTodoAdapter() : TodoAdapter = TodoAdapter()
}
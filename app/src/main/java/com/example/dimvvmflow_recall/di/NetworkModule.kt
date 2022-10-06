package com.example.dimvvmflow_recall.di

import com.example.dimvvmflow_recall.network.ApiHelper
import com.example.dimvvmflow_recall.network.ApiHelperImpt
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Singleton
    @Binds
    abstract fun bindApiHelper(
        apiHelperImpt: ApiHelperImpt
    ): ApiHelper
}
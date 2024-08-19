package com.shqadri.lokal.di

import com.shqadri.lokal.data.JobsRepository
import com.shqadri.lokal.data.JobsRepositoryImpl
import com.shqadri.lokal.network.JobsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideJobRepository(apiService: JobsApiService): JobsRepository =
        JobsRepositoryImpl(apiService = apiService)

}
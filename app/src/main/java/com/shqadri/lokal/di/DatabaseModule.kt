package com.shqadri.lokal.di

import android.content.Context
import androidx.room.Room
import com.shqadri.lokal.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()

    @Provides
    @Singleton
    fun providesDao(database: AppDatabase) = database.bookmarkDao()
}
package com.example.eventmaster.di

import android.content.Context
import com.example.eventmaster.data.local.dao.CategoryDao
import com.example.eventmaster.data.local.dao.EventDao
import com.example.eventmaster.data.local.database.EventMasterDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): EventMasterDatabase {
        return EventMasterDatabase.getInstance(context)
    }

    @Provides
    fun provideEventDao(database: EventMasterDatabase): EventDao {
        return database.eventDao()
    }

    @Provides
    fun provideCategoryDao(database: EventMasterDatabase): CategoryDao {
        return database.categoryDao()
    }
}

package com.ayodev.store_challenge.core.di

import android.content.Context
import androidx.room.Room
import com.ayodev.store_challenge.core.data.source.local.room.StoreDatabase
import com.ayodev.store_challenge.core.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): StoreDatabase =
        Room.databaseBuilder(
            context,
            StoreDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
}
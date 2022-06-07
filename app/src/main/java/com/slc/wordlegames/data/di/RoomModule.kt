package com.slc.wordlegames.data.di

import android.content.Context
import androidx.room.Room
import com.slc.wordlegames.data.database.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "history"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, LocalDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideHistoryDao(db: LocalDatabase) = db.getHistoryDao()

    @Singleton
    @Provides
    fun provideHiddenDao(db: LocalDatabase) = db.getHiddenDao()

}
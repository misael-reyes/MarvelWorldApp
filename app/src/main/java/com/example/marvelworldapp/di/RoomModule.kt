package com.example.marvelworldapp.di

import android.content.Context
import androidx.room.Room
import com.example.marvelworldapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val APP_DATABASE_NAME = "marvel_database"

    // vamos a proveer room
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME).build()

    // vamos a proveer una instancia por cada dao que tenemos en la capa de datos

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideComicDao(db: AppDatabase) = db.comicDao()

    @Singleton
    @Provides
    fun provideCreatorDao(db: AppDatabase) = db.creatorDao()

    @Singleton
    @Provides
    fun provideSeriesDao(db: AppDatabase) = db.seriesDao()

    @Singleton
    @Provides
    fun provideCharacterComicDao(db: AppDatabase) = db.characterComicDao()

    @Singleton
    @Provides
    fun provideCharacterSeriesDao(db: AppDatabase) = db.characterSeriesDao()

    @Singleton
    @Provides
    fun provideComicCreatorDao(db: AppDatabase) = db.comicCreatorDao()

    @Singleton
    @Provides
    fun provideSeriesComicDao(db: AppDatabase) = db.seriesComicDao()
}
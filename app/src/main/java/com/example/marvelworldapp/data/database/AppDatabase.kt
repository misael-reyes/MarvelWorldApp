package com.example.marvelworldapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelworldapp.data.database.dao.*
import com.example.marvelworldapp.data.database.entities.*


/**
 * clase para crear la base de datos
 */

@Database(
    entities = [
        CharacterEntity::class,
        ComicEntity::class,
        CreatorEntity::class,
        SeriesEntity::class,
        CharacterComicEntity::class,
        CharacterSeriesEntity::class,
        ComicCreatorEntity::class,
        SeriesComicEntity::class
               ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    /**
     * aqui vamos a tener las instancias de los daos
     */

    abstract fun characterDao(): CharacterDao

    abstract fun comicDao(): ComicDao

    abstract fun creatorDao(): CreatorDao

    abstract fun seriesDao(): SeriesDao

    abstract fun characterComicDao(): CharacterComicDao

    abstract fun characterSeriesDao(): CharacterSeriesDao

    abstract fun comicCreatorDao(): ComicCreatorDao

    abstract fun seriesComicDao(): SeriesComicDao
}
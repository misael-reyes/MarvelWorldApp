package com.example.marvelworldapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelworldapp.data.database.entities.SeriesComicEntity

@Dao
interface SeriesComicDao {

    /**
     * relación muchos a muchos entre series y comics
     */

    // consulta para conocer las series de un comic
    @Query("SELECT series_id FROM series_comics WHERE comic_id = :id_comic")
    suspend fun getAllIdSeries(id_comic: Int): List<Int>

    // consulta para conocer los comics de una serie
    @Query("SELECT comic_id FROM series_comics WHERE series_id = :id_series")
    suspend fun getAllIdComics(id_series: Int): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRelationsComicsOfSeries(relations: List<SeriesComicEntity>)
}
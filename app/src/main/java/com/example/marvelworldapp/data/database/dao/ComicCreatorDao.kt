package com.example.marvelworldapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelworldapp.data.database.entities.ComicCreatorEntity

@Dao
interface ComicCreatorDao {

    /**
     * relación muchos a muchos entre comic y creator
     */

    // consulta para concer los comics que ha creado un creador
    @Query("SELECT comic_id FROM comics_creators WHERE creator_id = :id_creator")
    suspend fun getAllIdComics(id_creator: Int): List<Int>

    // consulta para conocer a los creadores de un comic
    @Query("SELECT creator_id FROM comics_creators WHERE comic_id = :id_comic")
    suspend fun getAllIdCreators(id_comic: Int): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRelationsCreatorsOfComic(relations: List<ComicCreatorEntity>)
}
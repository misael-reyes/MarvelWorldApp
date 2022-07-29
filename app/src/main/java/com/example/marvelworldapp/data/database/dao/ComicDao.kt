package com.example.marvelworldapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelworldapp.data.database.entities.ComicEntity

@Dao
interface ComicDao {

    @Query("SELECT * FROM comics")
    suspend fun getAllComics(): List<ComicEntity>

    @Query("SELECT * FROM comics WHERE id_comic_api = :id_comic")
    suspend fun getComicById(id_comic: Int): ComicEntity

    @Query("DELETE FROM comics")
    suspend fun deleteAllComics()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllComics(comics: List<ComicEntity>)

}
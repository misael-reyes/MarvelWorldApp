package com.example.marvelworldapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelworldapp.data.database.entities.CreatorEntity

@Dao
interface CreatorDao {

    @Query("SELECT * FROM creators")
    suspend fun getAllCreators(): List<CreatorEntity>

    @Query("SELECT * FROM creators WHERE id_creator_api = :id_creator")
    suspend fun getCreatorById(id_creator: Int): CreatorEntity

    @Query("DELETE FROM creators")
    suspend fun deleteAllCreators()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCreators(creators: List<CreatorEntity>)
}
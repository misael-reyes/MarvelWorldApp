package com.example.marvelworldapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelworldapp.data.database.entities.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()

    @Query("SELECT * FROM characters WHERE id_character_api = :id_character")
    suspend fun findByIdCharacter(id_character: Int): CharacterEntity

    @Query("SELECT * FROM characters WHERE name = :nameCharacter")
    suspend fun findByNameCharacter(nameCharacter: String): List<CharacterEntity>
}
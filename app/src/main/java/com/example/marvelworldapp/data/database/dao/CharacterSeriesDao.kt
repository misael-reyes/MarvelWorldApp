package com.example.marvelworldapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelworldapp.data.database.entities.CharacterSeriesEntity

@Dao
interface CharacterSeriesDao {

    /**
     * relación muchos a muchos entre Character y Series
     */

    // consulta para conocer las series en las que aparece un personaje
    @Query("SELECT series_id FROM characters_series WHERE character_id = :id_character")
    suspend fun getAllIdSeries(id_character: Int): List<Int>

    // consulta para conocer a los personajes que aparecen en una serie
    @Query("SELECT character_id FROM characters_series WHERE series_id = :id_series")
    suspend fun getAllIdCharacters(id_series: Int): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRalationsSeriesOfCharacter(relations: List<CharacterSeriesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRelationsCharactersOfSeries(relations: List<CharacterSeriesEntity>)
}
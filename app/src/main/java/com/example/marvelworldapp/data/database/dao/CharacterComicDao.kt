package com.example.marvelworldapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelworldapp.data.database.entities.CharacterComicEntity

@Dao
interface CharacterComicDao {

    /**
     * relación muchos a muchos entre Character y Comic
     */

    // consulta para conocer a los personajes de un comic
    @Query("SELECT character_id FROM characters_comics WHERE comic_id = :id_comic")
    suspend fun getAllIdCharacters(id_comic: Int): List<Int>

    // consulta para concer los comics en los que aparece un personaje
    @Query("SELECT comic_id FROM characters_comics WHERE character_id = :id_character")
    suspend fun getAllIdComics(id_character: Int): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRelationsCharactersOfComic(relations: List<CharacterComicEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRelationsComicsOfCharacter(relations: List<CharacterComicEntity>)


}
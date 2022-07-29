package com.example.marvelworldapp.data.database

import android.util.Log
import com.example.marvelworldapp.data.database.dao.CharacterComicDao
import com.example.marvelworldapp.data.database.entities.CharacterComicEntity
import com.example.marvelworldapp.data.database.entities.CharacterSeriesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterComicLocalDataSource @Inject constructor(
    private val dao: CharacterComicDao
) {

    suspend fun getAllIdCharacters(id_comic: Int): List<Int> {
        return withContext(Dispatchers.IO) {
            dao.getAllIdCharacters(id_comic)
        }
    }

    suspend fun getAllIdComics(id_character: Int): List<Int> {
        return withContext(Dispatchers.IO) {
            dao.getAllIdComics(id_character)
        }
    }

    suspend fun insertAllRelationsCharactersOfComic(list: List<CharacterComicEntity>) {
        return withContext(Dispatchers.IO) {
            dao.insertAllRelationsCharactersOfComic(list)
        }
    }

    suspend fun insertAllRelationsComicsOfCharacter(list: List<CharacterComicEntity>) {
        return withContext(Dispatchers.IO) {
            dao.insertAllRelationsComicsOfCharacter(list)
        }
    }

}
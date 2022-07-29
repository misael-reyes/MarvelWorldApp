package com.example.marvelworldapp.data.database

import com.example.marvelworldapp.data.database.dao.CharacterDao
import com.example.marvelworldapp.data.database.entities.CharacterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersLocalDataSource @Inject constructor(
    private val characterDao: CharacterDao
) {

    suspend fun getAllCharacters(): List<CharacterEntity> {
        return withContext(Dispatchers.IO) {
            characterDao.getAllCharacters()
        }
    }

    suspend fun insertAllCharacters(lista: List<CharacterEntity>) {
        withContext(Dispatchers.IO) {
            characterDao.insertAllCharacters(lista)
        }
    }

    suspend fun deleteAllCharacters() {
        withContext(Dispatchers.IO) {
            characterDao.deleteAllCharacters()
        }
    }

    suspend fun getByIdCharacter(id_character: Int): CharacterEntity {
        return withContext(Dispatchers.IO) {
            characterDao.findByIdCharacter(id_character)
        }
    }

    suspend fun getCharacterByName(nameCharacter: String): List<CharacterEntity> {
        return withContext(Dispatchers.IO) {
            characterDao.findByNameCharacter(nameCharacter)
        }
    }
}
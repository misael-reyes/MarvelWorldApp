package com.example.marvelworldapp.data.database

import com.example.marvelworldapp.data.database.dao.CharacterSeriesDao
import com.example.marvelworldapp.data.database.entities.CharacterSeriesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterSeriesLocalDataSource @Inject constructor(
    private val dao: CharacterSeriesDao
) {

    suspend fun getAllIdCharacters(id_series: Int): List<Int> {
        return withContext(Dispatchers.IO) {
            dao.getAllIdCharacters(id_series)
        }
    }

    suspend fun getAllIdSeries(id_character: Int): List<Int> {
        return withContext(Dispatchers.IO) {
            dao.getAllIdSeries(id_character)
        }
    }

    suspend fun insertAllRelationsSeriesOfCharacter(list: List<CharacterSeriesEntity>) {
        return withContext(Dispatchers.IO) {
            dao.insertAllRalationsSeriesOfCharacter(list)
        }
    }

    suspend fun insertAllRelationsCharactersOfSeries(list: List<CharacterSeriesEntity>) {
        return withContext(Dispatchers.IO) {
            dao.insertAllRelationsCharactersOfSeries(list)
        }
    }
}
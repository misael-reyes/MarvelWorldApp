package com.example.marvelworldapp.data.network

import android.util.Log
import com.example.marvelworldapp.data.model.Image
import com.example.marvelworldapp.data.model.ObjectList
import com.example.marvelworldapp.data.model.character.Character
import com.example.marvelworldapp.data.model.character.CharacterDataContainer
import com.example.marvelworldapp.data.model.character.CharacterDataWrapper
import com.example.marvelworldapp.data.model.comic.ComicDataWrapper
import com.example.marvelworldapp.data.model.series.SeriesDataWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * clase de fuente de datos network como lo sugiere la guia de arquitectura
 * de android
 */
class CharactersNetworkDataSource @Inject constructor(
    private val api: ApiClient
) {

    suspend fun getAllCharacters(): CharacterDataWrapper {
        return withContext(Dispatchers.IO) {
            api.getAllCharacters()
        }
    }

    suspend fun getCharacterById(id_character: Int): CharacterDataWrapper {
        return withContext(Dispatchers.IO) {
            api.getCharacterById(id_character)
        }
    }

    suspend fun getCharactersOfComic(idComic: Int): CharacterDataWrapper {
        return withContext(Dispatchers.IO) {
            api.getCharactersOfComic(idComic)
        }
    }

    suspend fun getCharactersOfSeries(idSeries: Int): CharacterDataWrapper {
        return withContext(Dispatchers.IO) {
            api.getCharactersOfSeries(idSeries)
        }
    }

    suspend fun getCharacterByName(nameStartsWith: String): CharacterDataWrapper {
        return withContext(Dispatchers.IO) {
            api.getCharacterByName(nameStartsWith = nameStartsWith)
        }
    }
}
package com.example.marvelworldapp.data.network

import com.example.marvelworldapp.data.model.comic.ComicDataWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ComicsNetWorkDataSource @Inject constructor(
    private val api: ApiClient
) {

    suspend fun getAllComics(): ComicDataWrapper {
        return withContext(Dispatchers.IO) {
            api.getAllComics()
        }
    }

    suspend fun getComicsOfCharacter(idCharacter: Int): ComicDataWrapper {
        return withContext(Dispatchers.IO) {
            api.getComicsOfCharacter(idCharacter)
        }
    }

    suspend fun getComicById(idComic: Int): ComicDataWrapper {
        return withContext(Dispatchers.IO) {
            api.getComicById(idComic)
        }
    }

    suspend fun getComicsOfSeries(idSeries: Int): ComicDataWrapper {
        return withContext(Dispatchers.IO) {
            api.getComicsOfSeries(idSeries)
        }
    }
}
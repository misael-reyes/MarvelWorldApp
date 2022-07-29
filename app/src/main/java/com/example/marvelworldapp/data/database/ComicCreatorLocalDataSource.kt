package com.example.marvelworldapp.data.database

import com.example.marvelworldapp.data.database.dao.ComicCreatorDao
import com.example.marvelworldapp.data.database.entities.ComicCreatorEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ComicCreatorLocalDataSource @Inject constructor(
    private val dao: ComicCreatorDao
) {

    suspend fun getAllIdComics(id_creator: Int): List<Int> {
        return withContext(Dispatchers.IO) {
            dao.getAllIdComics(id_creator)
        }
    }

    suspend fun getAllIdCreators(id_comic: Int): List<Int> {
        return withContext(Dispatchers.IO) {
            dao.getAllIdCreators(id_comic)
        }
    }

    suspend fun insertAllRelationsCreatorsOfComic(list: List<ComicCreatorEntity>) {
        return withContext(Dispatchers.IO) {
            dao.insertRelationsCreatorsOfComic(list)
        }
    }
}
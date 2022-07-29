package com.example.marvelworldapp.data.database

import com.example.marvelworldapp.data.database.dao.ComicDao
import com.example.marvelworldapp.data.database.entities.ComicEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * clase que proveera datao de los comics que se encuantran a nivel de BD local
 */
class ComicsLocalDataSource @Inject constructor(
    private val comicDao: ComicDao
) {

    suspend fun getAllComics(): List<ComicEntity> {
        return withContext(Dispatchers.IO) {
            comicDao.getAllComics()
        }
    }

    suspend fun insertAllComics(list: List<ComicEntity>) {
        withContext(Dispatchers.IO) {
            comicDao.insertAllComics(list)
        }
    }

    suspend fun deleteAllComics() {
        withContext(Dispatchers.IO) {
            comicDao.deleteAllComics()
        }
    }

    suspend fun getComicById(id_comic: Int): ComicEntity {
        return withContext(Dispatchers.IO) {
            comicDao.getComicById(id_comic)
        }
    }

}
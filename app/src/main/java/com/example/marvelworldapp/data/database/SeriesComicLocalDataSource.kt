package com.example.marvelworldapp.data.database

import com.example.marvelworldapp.data.database.dao.SeriesComicDao
import com.example.marvelworldapp.data.database.entities.SeriesComicEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeriesComicLocalDataSource @Inject constructor(
    private val dao: SeriesComicDao
) {

    suspend fun getAllIdComics(id_series: Int): List<Int> {
        return withContext(Dispatchers.IO) {
            dao.getAllIdComics(id_series)
        }
    }

    suspend fun getAllIdSeries(id_comic: Int): List<Int> {
        return withContext(Dispatchers.IO) {
            dao.getAllIdSeries(id_comic)
        }
    }

    suspend fun insertAllRelationsComicsOfSeries(list: List<SeriesComicEntity>) {
        return withContext(Dispatchers.IO) {
            dao.insertAllRelationsComicsOfSeries(list)
        }
    }
}
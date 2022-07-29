package com.example.marvelworldapp.data.database

import com.example.marvelworldapp.data.database.dao.SeriesDao
import com.example.marvelworldapp.data.database.entities.SeriesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeriesLocalDataSource @Inject constructor(
    private val seriesDao: SeriesDao
) {

    suspend fun getAllSeries(): List<SeriesEntity> {
        return withContext(Dispatchers.IO) {
            seriesDao.getAllSeries()
        }
    }

    suspend fun insertAllSeries(list: List<SeriesEntity>) {
        withContext(Dispatchers.IO) {
            seriesDao.insertAllSeries(list)
        }
    }

    suspend fun deleteAllSeries() {
        withContext(Dispatchers.IO) {
            seriesDao.deleteAllSeries()
        }
    }

    suspend fun getSeriesById(id_series: Int): SeriesEntity {
        return withContext(Dispatchers.IO) {
            seriesDao.getSeriesById(id_series)
        }
    }
}
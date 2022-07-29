package com.example.marvelworldapp.data.network

import com.example.marvelworldapp.data.model.series.SeriesDataWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeriesNetWorkDataSource @Inject constructor(
    private val api: ApiClient
) {

    suspend fun getSeriesOfCharacter(idCharacter: Int): SeriesDataWrapper {
        return withContext(Dispatchers.IO) {
            api.getSeriesOfCharacter(idCharacter)
        }
    }

    suspend fun getAllSeries(): SeriesDataWrapper {
        return withContext(Dispatchers.IO) {
            api.getAllSeries()
        }
    }

    suspend fun getSeriesById(idSeries: Int): SeriesDataWrapper {
        return withContext(Dispatchers.IO) {
            api.getSeriesById(idSeries)
        }
    }
}
package com.example.marvelworldapp.data.repository

import com.example.marvelworldapp.data.database.CharacterSeriesLocalDataSource
import com.example.marvelworldapp.data.database.SeriesLocalDataSource
import com.example.marvelworldapp.data.database.entities.CharacterSeriesEntity
import com.example.marvelworldapp.data.database.entities.SeriesEntity
import com.example.marvelworldapp.data.network.SeriesNetWorkDataSource
import com.example.marvelworldapp.domain.model.SerieModel
import javax.inject.Inject

class SerieRepository @Inject constructor(
    private val api: SeriesNetWorkDataSource,
    private val daoSeries: SeriesLocalDataSource,
    private val daoCharacterSeries: CharacterSeriesLocalDataSource
) {

    /**
     * ******************************  PETICIONES A LA API DE MARVEL  ***************************************
     */

    /**
     * consultamos las series de un personaje al servidor de marvel
     */
    suspend fun getSeriesOfCharacterFromApi(id_character: Int): List<SerieModel> {
        val response = api.getSeriesOfCharacter(id_character)
        return response.data.results.map {
            it.toSerieModel()
        }
    }

    /**
     * consulta para retornar todas las series
     */
    suspend fun getAllSeriesFromApi(): List<SerieModel> {
        val response = api.getAllSeries()
        return response.data.results.map {
            it.toSerieModel()
        }
    }

    /**
     * consulta para retornar a una serie en especifico
     */
    suspend fun getSeriesByIdFromApi(idSeries: Int): SerieModel {
        val response = api.getSeriesById(idSeries)
        return response.data.results[0].toSerieModel()
    }

    /**
     * *****************************  PETICIONES A LA BD LOCAL  ********************************************
     */

    /**
     * petición para retornar las seires de un personaje desde la base de datos
     */
    suspend fun getSeriesOfCharacterFromDatabase(id_character: Int): List<SerieModel> {
        val ids = daoCharacterSeries.getAllIdSeries(id_character)
        val series = arrayListOf<SeriesEntity>()
        ids.forEach {
            series.add(daoSeries.getSeriesById(it))
        }
        return series.map {
            it.toSeriesModel()
        }
    }

    suspend fun insertSeries(list: List<SeriesEntity>) {
        daoSeries.insertAllSeries(list)
    }

    suspend fun insertRelationsSeriesOfCharacter(list: List<CharacterSeriesEntity>) {
        daoCharacterSeries.insertAllRelationsSeriesOfCharacter(list)
    }

    /**
     * petición para recuperar a todas las series de la base de datos
     */
    suspend fun getAllSeriesFromDatabase(): List<SerieModel> {
        val response: List<SeriesEntity> = daoSeries.getAllSeries()
        return response.map {
            it.toSeriesModel()
        }
    }

    suspend fun getSeriesByIdFromDatabase(idSeries: Int): SerieModel {
        val response: SeriesEntity = daoSeries.getSeriesById(idSeries)
        return response.toSeriesModel()
    }
}
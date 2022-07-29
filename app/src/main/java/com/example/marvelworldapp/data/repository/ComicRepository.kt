package com.example.marvelworldapp.data.repository

import android.util.Log
import com.example.marvelworldapp.data.database.CharacterComicLocalDataSource
import com.example.marvelworldapp.data.database.ComicsLocalDataSource
import com.example.marvelworldapp.data.database.SeriesComicLocalDataSource
import com.example.marvelworldapp.data.database.entities.CharacterComicEntity
import com.example.marvelworldapp.data.database.entities.ComicEntity
import com.example.marvelworldapp.data.database.entities.SeriesComicEntity
import com.example.marvelworldapp.data.network.ComicsNetWorkDataSource
import com.example.marvelworldapp.domain.model.ComicModel
import javax.inject.Inject

class ComicRepository @Inject constructor(
    private val api: ComicsNetWorkDataSource,
    private val daoCharacterComic: CharacterComicLocalDataSource,
    private val daoSeriesComic: SeriesComicLocalDataSource,
    private val daoComic: ComicsLocalDataSource
) {

    /**
     * ******************************  PETICIONES A LA API DE MARVEL  ***************************************
     */

    /**
     * recupera los comics del servidor MARVEL
     */
    suspend fun getAllComicsFromApi(): List<ComicModel> {
        val response = api.getAllComics()
        return response.data.results.map {
            it.toComicModel()
        }
    }

    /**
     * consultamos los comics e un personajes al servidro de marvel
     */
    suspend fun getComicsOfCharacterFromApi(id_character: Int): List<ComicModel> {
        val response = api.getComicsOfCharacter(id_character)
        return response.data.results.map {
            it.toComicModel()
        }
    }

    /**
     * consultamos los comics de una serie al serividor de marvel
     */
    suspend fun getComicsOfSeriesFromApi(id_series: Int): List<ComicModel> {
        val response = api.getComicsOfSeries(id_series)
        return response.data.results.map {
            it.toComicModel()
        }
    }

    /**
     * consultamos un comic del serivdor de marvel
     */
    suspend fun getComicFromApi(id_comic: Int): ComicModel {
        val response = api.getComicById(id_comic)
        return response.data.results[0].toComicModel()
    }

    /**
     * *****************************  PETICIONES A LA BD LOCAL  ********************************************
     */

    /**
     * peticion para conocer los comics de un personaje en la base de datos
     */
    suspend fun getComicsOfCharacterFromDatabase(id_character: Int): List<ComicModel> {
        val ids = daoCharacterComic.getAllIdComics(id_character)
        val comics = arrayListOf<ComicEntity>()
        ids.forEach {
            comics.add(daoComic.getComicById(it))
        }
        return comics.map {
            it.toComicModel()
        }
    }

    suspend fun getComicsOfSeriesFromDatabase(id_series: Int): List<ComicModel> {
        val ids = daoSeriesComic.getAllIdComics(id_series)
        val comics = arrayListOf<ComicEntity>()
        ids.forEach {
            comics.add(daoComic.getComicById(it))
        }
        return comics.map {
            it.toComicModel()
        }
    }

    /**
     * insertamos a todos nuestro comics en la base de datos
     */
    suspend fun insertComics(comics: List<ComicEntity>) {
        daoComic.insertAllComics(comics)
    }

    suspend fun getAllComicsFromDatabase(): List<ComicModel> {
        val response = daoComic.getAllComics()
        return response.map {
            it.toComicModel()
        }
    }

    /**
     * insertamos en la tabla relaciono a los comics de un personaje
     */
    suspend fun insertAllRelationComicOfCharacter(list: List<CharacterComicEntity>) {
        daoCharacterComic.insertAllRelationsComicsOfCharacter(list)
    }

    suspend fun getComicByIdFromDatabase(id_comic: Int): ComicModel {
        val response: ComicEntity = daoComic.getComicById(id_comic)
        return response.toComicModel()
    }

    /**
     * insertamos la relación comics de una serie
     */
    suspend fun insertAllRelationComicsOfSeries(list: List<SeriesComicEntity>) {
        daoSeriesComic.insertAllRelationsComicsOfSeries(list)
    }

}
package com.example.marvelworldapp.data.repository

import android.util.Log
import com.example.marvelworldapp.data.database.CharacterComicLocalDataSource
import com.example.marvelworldapp.data.database.CharacterSeriesLocalDataSource
import com.example.marvelworldapp.data.database.CharactersLocalDataSource
import com.example.marvelworldapp.data.database.dao.CharacterComicDao
import com.example.marvelworldapp.data.database.entities.CharacterComicEntity
import com.example.marvelworldapp.data.database.entities.CharacterEntity
import com.example.marvelworldapp.data.database.entities.CharacterSeriesEntity
import com.example.marvelworldapp.data.network.CharactersNetworkDataSource
import com.example.marvelworldapp.domain.model.CharacterModel
import javax.inject.Inject

/**
 * clase repositorio para la entidad Character para acceder a la fuente de datos
 */
class CharacterRepository @Inject constructor(
    private val api: CharactersNetworkDataSource,
    private val dao: CharactersLocalDataSource,
    private val daoCharacterComic: CharacterComicLocalDataSource,
    private val daoCharacterSeries: CharacterSeriesLocalDataSource
) {

    /**
     * ******************************  PETICIONES A LA API DE MARVEL  ***************************************
     */

    suspend fun getByIdCharacterFromApi(id_character: Int): CharacterModel {
        val response = api.getCharacterById(id_character)
        return response.data.results[0].toCharacterModel()
    }

    /**
     * recupera los personajes del servidor de MARVEL
     */
    suspend fun getAllCharactersFromApi(): List<CharacterModel> {
        val response = api.getAllCharacters()
        return response.data.results.map {
            it.toCharacterModel()
        }
    }

    /**
     * consulta para retornar a los personaejs de un comic desde la api de marvel
     */
    suspend fun getCharactersOfComicFromApi(id_comic: Int): List<CharacterModel> {
        val response = api.getCharactersOfComic(id_comic)
        return response.data.results.map {
            it.toCharacterModel()
        }
    }

    /**
     * consulta para retornar a los comics de una series desde la api de marvel
     */
    suspend fun getCharactersOfSeriesFromApi(id_series: Int): List<CharacterModel> {
        val response = api.getCharactersOfSeries(id_series)
        return response.data.results.map {
            it.toCharacterModel()
        }
    }


    /**
     * *****************************  PETICIONES A LA BD LOCAL  ********************************************
     */

    /**
     * recupera los personajes de la base de datos local
     */
    suspend fun getAllCharactersFromDatabase(): List<CharacterModel> {
        val response: List<CharacterEntity> = dao.getAllCharacters()
        return response.map {
            it.toCharacterModel()
        }
    }

    /**
     * insertamos a todos nuestro personajes en la base de datos
     */
    suspend fun insertCharacters(characters: List<CharacterEntity>) {
        dao.insertAllCharacters(characters)
    }

    /**
     * eliminamos a todos los caracteres
     */
    suspend fun deleteCharacters() {
        dao.deleteAllCharacters()
    }

    /**
     * consultamos a un personaje de la BD local
     */
    suspend fun getByIdCharacterFromDatabase(id_character: Int): CharacterModel {
        val response: CharacterEntity = dao.getByIdCharacter(id_character)
        return response.toCharacterModel()
    }

    /**
     * personajes que han aparecido en un comic
     */
    suspend fun getCharactersOfComicFromDatabase(id_comic: Int): List<CharacterModel> {
        val response = daoCharacterComic.getAllIdCharacters(id_comic)
        val characters = arrayListOf<CharacterEntity>()
        response.forEach { id_character ->
            characters.add(dao.getByIdCharacter(id_character))
        }
        return characters.map {
            it.toCharacterModel()
        }
    }

    /**
     * personajes que han aparecido en una serie
     */
    suspend fun getCharactersOfSeriesFromDatabase(id_series: Int): List<CharacterModel> {
        val ids = daoCharacterSeries.getAllIdCharacters(id_series)
        val response = arrayListOf<CharacterEntity>()
        ids.forEach { id_character ->
            response.add(dao.getByIdCharacter(id_character))
        }
        return response.map {
            it.toCharacterModel()
        }
    }

    /**
     * insertamos las relaciones personajes de un comic
     */
    suspend fun insertRelationCharactersOfComic(list: List<CharacterComicEntity>) {
        daoCharacterComic.insertAllRelationsCharactersOfComic(list)
    }

    /**
     * insertamos las relaciones personajes de una serie
     */
    suspend fun insertRelationCharactersOfSeries(list: List<CharacterSeriesEntity>) {
        daoCharacterSeries.insertAllRelationsCharactersOfSeries(list)
    }

    suspend fun getCharacterByNameFromApi(name: String): List<CharacterModel> {
        val response = api.getCharacterByName(name)
        return response.data.results.map {
            it.toCharacterModel()
        }
    }

    suspend fun getCharacterByNameFromDatabase(nameCharacter: String): List<CharacterModel> {
        val response = dao.getCharacterByName(nameCharacter)
        return response.map {
            it.toCharacterModel()
        }
    }
}
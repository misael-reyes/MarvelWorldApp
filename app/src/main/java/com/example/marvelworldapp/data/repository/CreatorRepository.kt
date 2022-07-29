package com.example.marvelworldapp.data.repository

import com.example.marvelworldapp.data.database.ComicCreatorLocalDataSource
import com.example.marvelworldapp.data.database.CreatorsLocalDataSource
import com.example.marvelworldapp.data.database.entities.ComicCreatorEntity
import com.example.marvelworldapp.data.database.entities.CreatorEntity
import com.example.marvelworldapp.data.network.CreatorsNetWorkDataSource
import com.example.marvelworldapp.domain.model.CreatorModel
import javax.inject.Inject

class CreatorRepository @Inject constructor(
    private val api: CreatorsNetWorkDataSource,
    private val daoCreator: CreatorsLocalDataSource,
    private val daoComicCreator: ComicCreatorLocalDataSource
) {

    /**
     * función para retornar a todos los creadores de un comic dado, esto desde
     * la api de marvel
     */
    suspend fun getCreatorsOfComicFromApi(idComic: Int): List<CreatorModel> {
        val response = api.getCreatorsOfComic(idComic)
        return response.data.results.map {
            it.toCreatorModel()
        }
    }

    /**
     * *****************************  PETICIONES A LA BD LOCAL  ********************************************
     */

    /**
     * insertamos a los creadores en la bd
     */
    suspend fun insertCreators(list: List<CreatorEntity>) {
        daoCreator.insertAllCreators(list)
    }

    /**
     * insertamos las relaciones, creadores de un comic
     */
    suspend fun insertRelationsCreatorsOfComic(list: List<ComicCreatorEntity>) {
        daoComicCreator.insertAllRelationsCreatorsOfComic(list)
    }

    /**
     * dame los creadores de un comic
     */
    suspend fun getCreatorsOfComicFromDatabase(id_comic: Int): List<CreatorModel> {
        val ids = daoComicCreator.getAllIdCreators(id_comic)
        val creators = arrayListOf<CreatorEntity>()
        ids.forEach {
            creators.add(daoCreator.getCreatorById(it))
        }
        return creators.map {
            it.toCreatorModel()
        }
    }
}
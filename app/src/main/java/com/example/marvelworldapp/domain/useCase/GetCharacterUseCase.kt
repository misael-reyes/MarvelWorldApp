package com.example.marvelworldapp.domain.useCase

import android.util.Log
import com.example.marvelworldapp.data.database.entities.CharacterComicEntity
import com.example.marvelworldapp.data.database.entities.CharacterSeriesEntity
import com.example.marvelworldapp.data.repository.CharacterRepository
import com.example.marvelworldapp.data.repository.ComicRepository
import com.example.marvelworldapp.data.repository.SerieRepository
import com.example.marvelworldapp.domain.model.CharacterModel
import com.example.marvelworldapp.domain.model.ComicModel
import com.example.marvelworldapp.domain.model.SerieModel
import java.lang.Exception
import javax.inject.Inject

/**
 * caso de uso para retornar a un solo personaje, la llamada incluye
 * sus comics y series en las que ha participado
 */
class GetCharacterUseCase @Inject constructor(
    private val repositoryCharacter: CharacterRepository,
    private val repositorySerie: SerieRepository,
    private val repositoryComic: ComicRepository
) {
    suspend operator fun invoke(id_character: Int): CharacterModel {

        var character: CharacterModel? = null
        var series: List<SerieModel>? = null
        var comics: List<ComicModel>? = null

        try {
            character = repositoryCharacter.getByIdCharacterFromApi(id_character)
            series = repositorySerie.getSeriesOfCharacterFromApi(id_character)
            comics = repositoryComic.getComicsOfCharacterFromApi(id_character)
        } catch (e: Exception) {
            Log.i("errorconexion", e.message.toString())
        }

        return if (character != null && series != null && comics != null) {
            // si hubo respuesta

            // insertamos los comics y sus relaciones

            repositoryComic.insertComics(comics.map { it.toComicEntity() })
            val relations = arrayListOf<CharacterComicEntity>()
            comics.forEach {
                relations.add(CharacterComicEntity(id_character, it.id))
            }
            repositoryComic.insertAllRelationComicOfCharacter(relations)

            // insertamos las series y sus relaciones

            repositorySerie.insertSeries(series.map { it.toSeriesEntity() })
            val relationsSeries = arrayListOf<CharacterSeriesEntity>()
            series.forEach {
                relationsSeries.add(CharacterSeriesEntity(id_character, it.id))
            }
            repositorySerie.insertRelationsSeriesOfCharacter(relationsSeries)

            character.comics = comics
            character.series = series
            character
        } else {
            // no hubo respuesta
            character = repositoryCharacter.getByIdCharacterFromDatabase(id_character)
            character.comics = repositoryComic.getComicsOfCharacterFromDatabase(id_character)
            character.series = repositorySerie.getSeriesOfCharacterFromDatabase(id_character)
            character
        }
    }
}
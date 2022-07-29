package com.example.marvelworldapp.domain.useCase

import com.example.marvelworldapp.data.database.entities.CharacterSeriesEntity
import com.example.marvelworldapp.data.database.entities.SeriesComicEntity
import com.example.marvelworldapp.data.repository.CharacterRepository
import com.example.marvelworldapp.data.repository.ComicRepository
import com.example.marvelworldapp.data.repository.SerieRepository
import com.example.marvelworldapp.domain.model.CharacterModel
import com.example.marvelworldapp.domain.model.ComicModel
import com.example.marvelworldapp.domain.model.SerieModel
import java.lang.Exception
import javax.inject.Inject

class GetSeriesUseCase @Inject constructor(
    private val seriesRepository: SerieRepository,
    private val characterRepository: CharacterRepository,
    private val comicRepository: ComicRepository
) {

    suspend operator fun invoke(idSeries: Int): SerieModel {

        var series: SerieModel? = null
        var characters: List<CharacterModel>? = null
        var comics: List<ComicModel>? = null

        try {
            series = seriesRepository.getSeriesByIdFromApi(idSeries)
            characters = characterRepository.getCharactersOfSeriesFromApi(idSeries)
            comics = comicRepository.getComicsOfSeriesFromApi(idSeries)
        } catch (e: Exception) {
            //
        }

        return if (series != null && characters != null && comics != null) {
            // la petición a la API fue exitosa

            // insertamos a los personajes y sus relaciones

            characterRepository.insertCharacters(characters.map { it.toCharacterEntity() })
            val relationsCharacters = arrayListOf<CharacterSeriesEntity>()
            characters.forEach {
                relationsCharacters.add(CharacterSeriesEntity(it.id, idSeries))
            }
            characterRepository.insertRelationCharactersOfSeries(relationsCharacters)

            // insertamos a los comics y sus relaciones

            comicRepository.insertComics(comics.map { it.toComicEntity() })
            val relationsComics = arrayListOf<SeriesComicEntity>()
            comics.forEach {
                relationsComics.add(SeriesComicEntity(idSeries, it.id))
            }
            comicRepository.insertAllRelationComicsOfSeries(relationsComics)

            series.comics = comics
            series.characters = characters
            series
        } else {
            // no hubo respuesta

            series = seriesRepository.getSeriesByIdFromDatabase(idSeries)
            series.characters = characterRepository.getCharactersOfSeriesFromDatabase(idSeries)
            series.comics = comicRepository.getComicsOfSeriesFromDatabase(idSeries)
            series
        }
    }
}
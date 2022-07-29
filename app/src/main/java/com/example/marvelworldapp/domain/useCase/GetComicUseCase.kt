package com.example.marvelworldapp.domain.useCase

import com.example.marvelworldapp.data.database.entities.CharacterComicEntity
import com.example.marvelworldapp.data.database.entities.ComicCreatorEntity
import com.example.marvelworldapp.data.repository.CharacterRepository
import com.example.marvelworldapp.data.repository.ComicRepository
import com.example.marvelworldapp.data.repository.CreatorRepository
import com.example.marvelworldapp.domain.model.CharacterModel
import com.example.marvelworldapp.domain.model.ComicModel
import com.example.marvelworldapp.domain.model.CreatorModel
import javax.inject.Inject

/**
 * al retornar un comic, este incluye a sus creadores y personajes
 */
class GetComicUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val creatorRepository: CreatorRepository,
    private val comicRepository: ComicRepository
) {
    suspend operator fun invoke(id_comic: Int): ComicModel {
        /**
        val comic = comicRepository.getComicFromApi(id_comic)

        val creators = creatorRepository.getCreatorsOfComicFromApi(id_comic)

        val characters = characterRepository.getCharactersOfComicFromApi(id_comic)

        comic.characters = characters
        comic.creators = creators
        Log.i("prueba", comic.id.toString())
        return comic
         **/

        // variables que puden contener errores

        var comic: ComicModel? = null
        var characters: List<CharacterModel>? = null
        var creators: List<CreatorModel>? = null

        // consultamos suponiendo que puede arrojar errores la consulta

        try {
            comic = comicRepository.getComicFromApi(id_comic)
            characters = characterRepository.getCharactersOfComicFromApi(id_comic)
            creators = creatorRepository.getCreatorsOfComicFromApi(id_comic)
        } catch (e: Exception) {
            //
        }

        return if (comic != null && characters != null && creators != null) {
            // la respuesta fue exitosa

            // insertamos a los personajes y sus relaciones

            characterRepository.insertCharacters(characters.map { it.toCharacterEntity() })
            val relationCharacters = arrayListOf<CharacterComicEntity>()
            characters.forEach {
                relationCharacters.add(CharacterComicEntity(it.id, id_comic))
            }
            characterRepository.insertRelationCharactersOfComic(relationCharacters)

            // insertamos a los creadores y sus relaciones

            creatorRepository.insertCreators(creators.map { it.toCreatorEntity() })
            val relationCreators = arrayListOf<ComicCreatorEntity>()
            creators.forEach {
                relationCreators.add(ComicCreatorEntity(id_comic, it.id))
            }
            creatorRepository.insertRelationsCreatorsOfComic(relationCreators)

            comic.creators = creators
            comic.characters = characters
            comic
        } else {
            // no hubo respuesta

            comic = comicRepository.getComicByIdFromDatabase(id_comic)
            comic.characters = characterRepository.getCharactersOfComicFromDatabase(id_comic)
            comic.creators = creatorRepository.getCreatorsOfComicFromDatabase(id_comic)
            comic
        }

    }
}
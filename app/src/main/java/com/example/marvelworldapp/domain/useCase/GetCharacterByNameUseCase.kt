package com.example.marvelworldapp.domain.useCase

import com.example.marvelworldapp.data.repository.CharacterRepository
import com.example.marvelworldapp.domain.model.CharacterModel
import javax.inject.Inject

class GetCharacterByNameUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    suspend operator fun invoke(nameCharacter: String): List<CharacterModel> {

        /**
         * necesitamos solicitar a un personaje por su nombre
         */

        var characters: List<CharacterModel>? = null

        try {
            characters = characterRepository.getCharacterByNameFromApi(nameCharacter)
        } catch (e: Exception) {
            //
        }

        return if (characters != null) {
            characterRepository.insertCharacters(characters.map { it.toCharacterEntity() })
            characters
        } else {
            characterRepository.getCharacterByNameFromDatabase(nameCharacter)
        }
    }
}
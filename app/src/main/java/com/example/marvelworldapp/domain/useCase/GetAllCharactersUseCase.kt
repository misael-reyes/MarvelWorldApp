package com.example.marvelworldapp.domain.useCase

import android.util.Log
import com.example.marvelworldapp.data.repository.CharacterRepository
import com.example.marvelworldapp.domain.model.CharacterModel
import javax.inject.Inject

/**
 * caso de uso para solicitar a todos los personajes, no importa
 * la fuente de datos
 */
class GetAllCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(): List<CharacterModel> {

        var characters: List<CharacterModel>? = null
        try {
            characters = repository.getAllCharactersFromApi()
        } catch (e: Exception) {
            Log.i("errorconexion",e.message.toString())
        }


        return if (characters != null) {
            // si retorno algo la llamada
            //repository.deleteCharacters()
            repository.insertCharacters(characters.map { it.toCharacterEntity() })
            characters
        } else {
            repository.getAllCharactersFromDatabase()
        }
    }
}
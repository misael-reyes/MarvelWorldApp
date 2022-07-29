package com.example.marvelworldapp.domain.useCase

import com.example.marvelworldapp.data.repository.ComicRepository
import com.example.marvelworldapp.domain.model.ComicModel
import javax.inject.Inject

class GetAllComicsUseCase @Inject constructor(
    private val repository: ComicRepository
) {

    suspend operator fun invoke(): List<ComicModel> {

        //return repository.getAllComicsFromApi()

        var comics: List<ComicModel>? = null

        try {
            comics = repository.getAllComicsFromApi()
        } catch (e: Exception) {
            // cachamos la exepcion por si el dispositivo no se encuentra conectado a internet
        }

        return if (comics != null) {
            // la llamada a la api fue exitosa
            repository.insertComics(comics.map { it.toComicEntity() })
            comics
        } else
            repository.getAllComicsFromDatabase()
    }
}
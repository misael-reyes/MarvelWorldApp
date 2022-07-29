package com.example.marvelworldapp.data.model.series

import com.example.marvelworldapp.data.model.Image
import com.example.marvelworldapp.data.model.ObjectList
import com.example.marvelworldapp.domain.model.SerieModel


data class Serie(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Image,
    val comics: ObjectList,
    val characters: ObjectList
) {
    /**
     * función para convertir un objeto Serie a SerieModel
     */
    fun toSerieModel(): SerieModel {
        return SerieModel(
            id = id,
            title = title,
            description = when {
                description.isNullOrEmpty() -> "not description"
                else -> description
            },
            thumbnail = thumbnail.path,
            thumbnail_extension = thumbnail.extension,
            comicsURL = comics.collectionURI,
            charactersURL = characters.collectionURI,
            comics = emptyList(),
            characters = emptyList()
        )
    }
}
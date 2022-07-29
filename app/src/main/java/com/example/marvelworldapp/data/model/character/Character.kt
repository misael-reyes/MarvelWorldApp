package com.example.marvelworldapp.data.model.character

import com.example.marvelworldapp.data.model.Image
import com.example.marvelworldapp.data.model.ObjectList
import com.example.marvelworldapp.domain.model.CharacterModel

/**
 * clase objeto que representa a un personaje, la estructura es la que devuelve
 * la llamada a la API
 */
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val resourceURI: String,
    val thumbnail: Image,
    val comics: ObjectList,
    val series: ObjectList
) {
    /**
     * función para convertir un objeto Character a un objeto CharacterModel
     */
    fun toCharacterModel(): CharacterModel {
        return CharacterModel(
            id = id,
            name = name,
            description = description,
            thumbnail = thumbnail.path,
            thumbnail_extension = thumbnail.extension,
            comicsURL = comics.collectionURI,
            seriesURL = series.collectionURI,
            comics = emptyList(),
            series = emptyList()
        )
    }
}
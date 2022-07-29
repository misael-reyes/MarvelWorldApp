package com.example.marvelworldapp.domain.model

import com.example.marvelworldapp.data.database.entities.CharacterEntity

/**
 * Modelo que representa a un personaje, este modelo es el
 * que se usa en toda la capa de dominio y IU
 */
data class CharacterModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    val thumbnail_extension: String,
    // lo que voy almacenar en comicsURL y seriesURL es la url para acceder a los comics y series
    // de mi personaje respectivamente
    val comicsURL: String,
    val seriesURL: String,
    var comics: List<ComicModel>,
    var series: List<SerieModel>
) {
    fun toCharacterEntity(): CharacterEntity {
        return CharacterEntity(
            id_character_api = id,
            name = name,
            description = description,
            thumbnail = thumbnail,
            thumbnail_extension = thumbnail_extension,
            comicsURL = comicsURL,
            seriesURL = seriesURL
        )
    }
}
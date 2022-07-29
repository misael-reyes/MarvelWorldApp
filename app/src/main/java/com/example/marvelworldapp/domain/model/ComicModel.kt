package com.example.marvelworldapp.domain.model

import com.example.marvelworldapp.data.database.entities.ComicEntity

data class ComicModel(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val thumbnail_extension: String,
    val seriesURL: String,
    val charactersURL: String,
    var creators: List<CreatorModel>,
    var characters: List<CharacterModel>
) {
    fun toComicEntity(): ComicEntity {
        return ComicEntity(
            id_comic_api = id,
            title = title,
            description = description,
            thumbnail = thumbnail,
            thumbnail_extension = thumbnail_extension
        )
    }
}
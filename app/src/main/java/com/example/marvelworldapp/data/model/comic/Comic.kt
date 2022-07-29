package com.example.marvelworldapp.data.model.comic

import com.example.marvelworldapp.data.model.Image
import com.example.marvelworldapp.data.model.ObjectList
import com.example.marvelworldapp.data.model.ObjectSummary
import com.example.marvelworldapp.domain.model.ComicModel

data class Comic(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Image,
    val creators: ObjectList,
    val characters: ObjectList
) {
    /**
     * función para convertir un objeto Comic a un objeto ComicModel
     */
    fun toComicModel(): ComicModel {
        return ComicModel(
            id = id,
            title = title,
            description = when {
                description.isNullOrEmpty() -> "not description"
                else -> description
            },
            thumbnail = thumbnail.path,
            thumbnail_extension = thumbnail.extension,
            seriesURL = "",
            charactersURL = characters.collectionURI,
            creators = emptyList(),
            characters = emptyList()
        )
    }
}
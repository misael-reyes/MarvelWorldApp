package com.example.marvelworldapp.domain.model

import com.example.marvelworldapp.data.database.entities.SeriesEntity

data class SerieModel(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val thumbnail_extension: String,
    val comicsURL: String,
    val charactersURL: String,
    var comics: List<ComicModel>,
    var characters: List<CharacterModel>
) {
    fun toSeriesEntity(): SeriesEntity {
        return SeriesEntity(
            id_series_api = id,
            title = title,
            description = description,
            thumbnail = thumbnail,
            thumbnail_extension = thumbnail_extension
        )
    }
}
package com.example.marvelworldapp.domain.model

import com.example.marvelworldapp.data.database.entities.CreatorEntity

data class CreatorModel(
    val id: Int,
    val fullName: String,
    val thumbnail: String,
    val thumbnail_extension: String
) {
    fun toCreatorEntity(): CreatorEntity {
        return CreatorEntity(
            id_creator_api = id,
            fullName = fullName,
            thumbnail = thumbnail,
            thumbnail_extension = thumbnail_extension
        )
    }
}
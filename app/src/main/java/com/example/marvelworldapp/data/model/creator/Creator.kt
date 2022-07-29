package com.example.marvelworldapp.data.model.creator

import com.example.marvelworldapp.data.model.Image
import com.example.marvelworldapp.domain.model.CreatorModel

data class Creator(
    val id: Int,
    val fullName: String,
    val thumbnail: Image
) {
    fun toCreatorModel(): CreatorModel {
        return CreatorModel(
            id = id,
            fullName = fullName,
            thumbnail = thumbnail.path,
            thumbnail_extension = thumbnail.extension
        )
    }
}
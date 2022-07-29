package com.example.marvelworldapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "characters_comics",
    foreignKeys = [
        ForeignKey(
        entity = CharacterEntity::class,
        parentColumns = ["id_character_api"],
        childColumns = ["character_id"]
        ),
        ForeignKey(
            entity = ComicEntity::class,
            parentColumns = ["id_comic_api"],
            childColumns = ["comic_id"]
        )
    ],
    primaryKeys = ["character_id", "comic_id"]
)
data class CharacterComicEntity(
    @ColumnInfo(name = "character_id") val character_id: Int,
    @ColumnInfo(name = "comic_id") val comic_id: Int,
) {
}
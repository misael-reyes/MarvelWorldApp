package com.example.marvelworldapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelworldapp.domain.model.CharacterModel

/**
 * clae entidad para guardar un personajes
 */

@Entity(tableName = "characters")
data class CharacterEntity(
    //@ColumnInfo(name = "id_character") val id_character: Int = 0,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_character_api") val id_character_api: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "thumbnail_extension") val thumbnail_extension: String,
    @ColumnInfo(name = "comicsURL") val comicsURL: String,
    @ColumnInfo(name = "seriesURL") val seriesURL: String
) {
    fun toCharacterModel(): CharacterModel {
        return CharacterModel(
            id = id_character_api,
            name = name,
            description = description,
            thumbnail = thumbnail,
            thumbnail_extension = thumbnail_extension,
            comicsURL = comicsURL,
            seriesURL = seriesURL,
            comics = emptyList(),
            series = emptyList()
        )
    }
}
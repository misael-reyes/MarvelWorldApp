package com.example.marvelworldapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelworldapp.domain.model.ComicModel

/**
 * clase entidad para guardar a los comics
 */

@Entity(tableName = "comics")
data class ComicEntity(
    //@ColumnInfo(name = "id_comic") val id_comic: Int = 0,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_comic_api") val id_comic_api: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "thumbnail_extension") val thumbnail_extension: String
) {
    fun toComicModel(): ComicModel {
        return ComicModel(
            id = id_comic_api,
            title = title,
            description = description,
            thumbnail = thumbnail,
            thumbnail_extension = thumbnail_extension,
            seriesURL = "",
            charactersURL = "",
            creators = emptyList(),
            characters = emptyList()
        )
    }
}
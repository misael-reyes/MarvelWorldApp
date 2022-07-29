package com.example.marvelworldapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelworldapp.domain.model.SerieModel

@Entity(tableName = "series")
data class SeriesEntity(
    //@ColumnInfo(name = "id_series") val id_series: Int = 0,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_series_api") val id_series_api: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "thumbnail_extension") val thumbnail_extension: String
) {
    fun toSeriesModel(): SerieModel {
        return SerieModel(
            id = id_series_api,
            title = title,
            description = description,
            thumbnail = thumbnail,
            thumbnail_extension = thumbnail_extension,
            comicsURL = "",
            charactersURL = "",
            comics = emptyList(),
            characters = emptyList()
        )
    }
}
package com.example.marvelworldapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "series_comics",
    foreignKeys = [
        ForeignKey(
            entity = SeriesEntity::class,
            parentColumns = ["id_series_api"],
            childColumns = ["series_id"]
        ),
        ForeignKey(
            entity = ComicEntity::class,
            parentColumns = ["id_comic_api"],
            childColumns = ["comic_id"]
        )
    ], primaryKeys = ["series_id", "comic_id"]
)
data class SeriesComicEntity(
    @ColumnInfo(name = "series_id") val series_id: Int,
    @ColumnInfo(name = "comic_id") val comic_id: Int,
) {
}
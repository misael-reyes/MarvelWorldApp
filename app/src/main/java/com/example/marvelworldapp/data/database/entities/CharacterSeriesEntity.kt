package com.example.marvelworldapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "characters_series",
    foreignKeys = [
        ForeignKey(
            entity = CharacterEntity::class,
            parentColumns = ["id_character_api"],
            childColumns = ["character_id"]
        ),
        ForeignKey(
            entity = SeriesEntity::class,
            parentColumns = ["id_series_api"],
            childColumns = ["series_id"]
        )
    ], primaryKeys = ["character_id", "series_id"]
)
data class CharacterSeriesEntity(
    @ColumnInfo(name = "character_id") val character_id: Int,
    @ColumnInfo(name = "series_id") val series_id: Int,
) {
}
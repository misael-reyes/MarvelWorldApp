package com.example.marvelworldapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "comics_creators",
    foreignKeys = [
        ForeignKey(
            entity = ComicEntity::class,
            parentColumns = ["id_comic_api"],
            childColumns = ["comic_id"]
        ),
        ForeignKey(
            entity = CreatorEntity::class,
            parentColumns = ["id_creator_api"],
            childColumns = ["creator_id"]
        )
    ], primaryKeys = ["comic_id", "creator_id"]
)
data class ComicCreatorEntity(
    @ColumnInfo(name = "comic_id") val comic_id: Int,
    @ColumnInfo(name = "creator_id") val creator_id: Int,
) {
}
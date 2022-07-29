package com.example.marvelworldapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelworldapp.domain.model.CreatorModel

@Entity(tableName = "creators")
data class CreatorEntity(
    //@ColumnInfo(name = "id_creator") val id_creator: Int = 0,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_creator_api") val id_creator_api: Int,
    @ColumnInfo(name = "fullName") val fullName: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "thumbnail_extension") val thumbnail_extension: String
) {
    fun toCreatorModel(): CreatorModel {
        return CreatorModel(
            id = id_creator_api,
            fullName = fullName,
            thumbnail = thumbnail,
            thumbnail_extension = thumbnail_extension
        )
    }
}
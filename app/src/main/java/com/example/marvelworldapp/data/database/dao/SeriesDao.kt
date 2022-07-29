package com.example.marvelworldapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelworldapp.data.database.entities.SeriesEntity

@Dao
interface SeriesDao {

    @Query("SELECT * FROM series")
    suspend fun getAllSeries(): List<SeriesEntity>

    @Query("SELECT * FROM series WHERE id_series_api = :id_series")
    suspend fun getSeriesById(id_series: Int): SeriesEntity

    @Query("DELETE FROM series")
    suspend fun deleteAllSeries()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSeries(series: List<SeriesEntity>)
}
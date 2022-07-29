package com.example.marvelworldapp.domain.useCase

import com.example.marvelworldapp.data.repository.SerieRepository
import com.example.marvelworldapp.domain.model.SerieModel
import javax.inject.Inject

class GetAllSeriesUseCase @Inject constructor(
    private val seriesRepository: SerieRepository
) {

    suspend operator fun invoke(): List<SerieModel> {

        var series: List<SerieModel>? = null

        try {
            series = seriesRepository.getAllSeriesFromApi()
        } catch (e: Exception) {
            //
        }

        return if (series != null) {
            seriesRepository.insertSeries(series.map { it.toSeriesEntity() })
            series
        } else {
            seriesRepository.getAllSeriesFromDatabase()
        }
    }
}
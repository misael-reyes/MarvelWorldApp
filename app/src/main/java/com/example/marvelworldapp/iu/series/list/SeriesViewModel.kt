package com.example.marvelworldapp.iu.series.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelworldapp.domain.model.SerieModel
import com.example.marvelworldapp.domain.useCase.GetAllSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val casoUso: GetAllSeriesUseCase
) : ViewModel() {

    // live data para la lista de series

    private val _series = MutableLiveData<List<SerieModel>>()
    val series: LiveData<List<SerieModel>> get() = _series

    // live data para el status del progress bar

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getAllSeries() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = casoUso()
            _isLoading.value = false
            _series.value = response
        }
    }
}
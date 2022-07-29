package com.example.marvelworldapp.iu.series.detail

import androidx.lifecycle.*
import com.example.marvelworldapp.domain.model.SerieModel
import com.example.marvelworldapp.domain.useCase.GetSeriesUseCase
import kotlinx.coroutines.launch

class DetailSeriesViewModel(
    private val idSeries: Int,
    private val getSeriesUseCase: GetSeriesUseCase
) : ViewModel() {

    private val _series = MutableLiveData<SerieModel>()
    val series: LiveData<SerieModel> get() = _series

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> get() = _isLoading

    fun getSeries() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = getSeriesUseCase(idSeries)
            _isLoading.value = false
            _series.value = response
        }
    }

}

class DetailSeriesViewModelFactory(
    private val idSeries: Int,
    private val getSeriesUseCase: GetSeriesUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailSeriesViewModel(idSeries, getSeriesUseCase) as T
    }
}
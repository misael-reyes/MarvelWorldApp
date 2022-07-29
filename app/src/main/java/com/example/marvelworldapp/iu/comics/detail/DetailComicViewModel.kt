package com.example.marvelworldapp.iu.comics.detail

import androidx.lifecycle.*
import com.example.marvelworldapp.domain.model.ComicModel
import com.example.marvelworldapp.domain.useCase.GetComicUseCase
import kotlinx.coroutines.launch

class DetailComicViewModel(
    private val idComic: Int,
    private val getComicUseCase: GetComicUseCase
) : ViewModel() {

    private val _comic = MutableLiveData<ComicModel>()
    val comic: LiveData<ComicModel> get() = _comic

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> get() = _isLoading

    fun getComic() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = getComicUseCase(idComic)
            _isLoading.value = false
            _comic.value = response
        }
    }
}

class DetailComicViewModelFactory(
    private val comicId: Int,
    private val getComicUseCase: GetComicUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailComicViewModel(comicId, getComicUseCase) as T
    }
}
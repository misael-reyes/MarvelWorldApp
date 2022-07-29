package com.example.marvelworldapp.iu.comics.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelworldapp.domain.model.ComicModel
import com.example.marvelworldapp.domain.useCase.GetAllComicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val getAllComicsUseCase: GetAllComicsUseCase
) : ViewModel() {

    private val _comics = MutableLiveData<List<ComicModel>>()
    val comics: LiveData<List<ComicModel>> get() = _comics

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> get() = _isLoading

    /**
     * funcion para traer a todos los comics
     */
    fun getComics() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = getAllComicsUseCase()
            _isLoading.value = false
            _comics.value = response
        }
    }
}
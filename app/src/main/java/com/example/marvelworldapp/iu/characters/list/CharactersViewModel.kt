package com.example.marvelworldapp.iu.characters.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelworldapp.domain.model.CharacterModel
import com.example.marvelworldapp.domain.useCase.GetAllCharactersUseCase
import com.example.marvelworldapp.domain.useCase.GetCharacterByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharacterByNameUseCase: GetCharacterByNameUseCase
) : ViewModel() {

    private val _characters = MutableLiveData<List<CharacterModel>>()
    val characters: LiveData<List<CharacterModel>> get() = _characters

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> get() = _isLoading

    /**
     * función para obtener a los personajes
     */
    fun getCharacters() {
        viewModelScope.launch {
            _characters.value = emptyList()
            _isLoading.value = true
            val response = getAllCharactersUseCase()
            _isLoading.value = false
            _characters.value = response
        }
    }

    fun findCharacterByName(nameCharacter: String) {
        viewModelScope.launch {
            _characters.value = emptyList()
            _isLoading.value = true
            val response = getCharacterByNameUseCase(nameCharacter)
            _isLoading.value = false
            _characters.value = response
        }
    }
}
package com.example.marvelworldapp.iu.characters.detail

import androidx.lifecycle.*
import com.example.marvelworldapp.domain.model.CharacterModel
import com.example.marvelworldapp.domain.useCase.GetCharacterUseCase
import kotlinx.coroutines.launch

class DetailCharacterViewModel(
    private val idCharacter: Int,
    private val getCharacterUseCase: GetCharacterUseCase
): ViewModel() {

    private val _character = MutableLiveData<CharacterModel>()
    val character: LiveData<CharacterModel> get() = _character

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> get() = _isLoading


    fun getCharacter() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = getCharacterUseCase(idCharacter)
            _isLoading.value = false
            _character.value = response
        }
    }
}

class DetailCharacterViewModelFactory(private val character: Int, private val getCharacterUseCase: GetCharacterUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailCharacterViewModel(character , getCharacterUseCase) as T
    }
}

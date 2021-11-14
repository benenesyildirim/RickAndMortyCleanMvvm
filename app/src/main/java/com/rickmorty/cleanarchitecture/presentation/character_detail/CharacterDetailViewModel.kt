package com.rickmorty.cleanarchitecture.presentation.character_detail

import androidx.lifecycle.*
import com.rickmorty.cleanarchitecture.common.Constants.PARAM_CHARACTER_ID
import com.rickmorty.cleanarchitecture.common.Resource
import com.rickmorty.cleanarchitecture.data.remote.dto.CharacterDto
import com.rickmorty.cleanarchitecture.domain.use_case.get_character.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        savedStateHandle.get<Int>(PARAM_CHARACTER_ID)?.let { characterId ->
            getCharacter(characterId.toString())
        }
    }

    private val _characterLiveData = MutableLiveData<Resource<CharacterDto>>()
    val characterLiveData: LiveData<Resource<CharacterDto>> get() = _characterLiveData

    private fun getCharacter(characterId: String) = viewModelScope.launch {
        try {
            getCharacterUseCase.getCharacter(characterId).let {
                _characterLiveData.postValue(Resource.Loading())

                delay(3000) //Added for just because of the showing loading animation
                _characterLiveData.postValue(Resource.Success(it.body()!!))
            }
        } catch (e: HttpException) {
            _characterLiveData.postValue(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            _characterLiveData.postValue(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
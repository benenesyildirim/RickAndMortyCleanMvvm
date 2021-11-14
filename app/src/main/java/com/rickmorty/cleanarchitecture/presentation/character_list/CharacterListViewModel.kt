package com.rickmorty.cleanarchitecture.presentation.character_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickmorty.cleanarchitecture.common.Resource
import com.rickmorty.cleanarchitecture.data.remote.dto.CharacterResponseDto
import com.rickmorty.cleanarchitecture.domain.use_case.get_characters.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    init {
        getCharacters()
    }

    private val _charactersLiveData = MutableLiveData<Resource<CharacterResponseDto>>()
    val charactersLiveData: LiveData<Resource<CharacterResponseDto>> get() = _charactersLiveData

    private fun getCharacters() = viewModelScope.launch {
        try {
            getCharactersUseCase.getCharacters().let {
                _charactersLiveData.postValue(Resource.Loading())

                delay(3000) //Added for just because of the showing loading animation
                _charactersLiveData.postValue(Resource.Success(it.body()!!))
            }
        } catch (e: HttpException) {
            _charactersLiveData.postValue(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            _charactersLiveData.postValue(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
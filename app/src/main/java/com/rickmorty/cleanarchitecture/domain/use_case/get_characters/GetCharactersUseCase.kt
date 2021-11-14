package com.rickmorty.cleanarchitecture.domain.use_case.get_characters

import com.rickmorty.cleanarchitecture.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend fun getCharacters() = repository.getCharacters()
}
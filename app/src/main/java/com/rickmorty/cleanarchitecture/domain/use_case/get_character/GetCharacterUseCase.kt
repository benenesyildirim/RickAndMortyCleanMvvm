package com.rickmorty.cleanarchitecture.domain.use_case.get_character

import com.rickmorty.cleanarchitecture.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend fun getCharacter(characterId: String) = repository.getCharacter(characterId)
}
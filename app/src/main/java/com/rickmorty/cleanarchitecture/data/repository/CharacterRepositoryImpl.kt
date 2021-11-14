package com.rickmorty.cleanarchitecture.data.repository

import com.rickmorty.cleanarchitecture.data.remote.CharacterPaprikaApi
import com.rickmorty.cleanarchitecture.data.remote.dto.CharacterDto
import com.rickmorty.cleanarchitecture.data.remote.dto.CharacterResponseDto
import com.rickmorty.cleanarchitecture.domain.repository.CharacterRepository
import retrofit2.Response
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: CharacterPaprikaApi
) : CharacterRepository {
    override suspend fun getCharacters(): Response<CharacterResponseDto> = api.getCharacters()

    override suspend fun getCharacter(characterId: String): Response<CharacterDto> =
        api.getCharacterDetail(characterId)
}
package com.rickmorty.cleanarchitecture.domain.repository

import com.rickmorty.cleanarchitecture.data.remote.dto.CharacterDto
import com.rickmorty.cleanarchitecture.data.remote.dto.CharacterResponseDto
import retrofit2.Response

interface CharacterRepository {

    suspend fun getCharacters(): Response<CharacterResponseDto>

    suspend fun getCharacter(characterId: String): Response<CharacterDto>
}
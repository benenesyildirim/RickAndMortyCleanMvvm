package com.rickmorty.cleanarchitecture.data.remote

import com.rickmorty.cleanarchitecture.data.remote.dto.CharacterDto
import com.rickmorty.cleanarchitecture.data.remote.dto.CharacterResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterPaprikaApi {

    @GET("/api/character")
    suspend fun getCharacters(): Response<CharacterResponseDto>

    @GET("/api/character/{characterId}")
    suspend fun getCharacterDetail(@Path("characterId") characterId: String): Response<CharacterDto>
}
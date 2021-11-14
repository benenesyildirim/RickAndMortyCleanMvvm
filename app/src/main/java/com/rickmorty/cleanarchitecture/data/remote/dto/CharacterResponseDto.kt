package com.rickmorty.cleanarchitecture.data.remote.dto

data class CharacterResponseDto(
    val info: Info,
    val results: List<CharacterDto>
)
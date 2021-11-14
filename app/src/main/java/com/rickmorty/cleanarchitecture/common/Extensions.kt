package com.rickmorty.cleanarchitecture.common

import com.rickmorty.cleanarchitecture.data.remote.dto.CharacterDto
import com.rickmorty.cleanarchitecture.data.remote.dto.CharacterResponseDto
import com.rickmorty.cleanarchitecture.domain.model.Character

object Extensions {
    fun CharacterDto.toCharacter(): Character {
        return Character(gender, id, image, name, species, status, type)
    }

    fun CharacterResponseDto.toCharacterList(): List<Character>{
        val characterList = ArrayList<Character>()
        for(i in results){
            characterList.add(i.toCharacter())
        }
        return characterList
    }
}
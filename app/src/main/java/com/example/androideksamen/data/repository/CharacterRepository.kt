package com.example.androideksamen.data.repository

import com.example.androideksamen.data.local.CharacterDao
import com.example.androideksamen.data.local.CharacterEntity

class CharacterRepository(
    private val dao: CharacterDao
) {

    fun getCharacters() = dao.getAllCharacters()

    fun getSavedCharacters() = dao.getAllCharacters()

    suspend fun insert(character: CharacterEntity) =
        dao.insert(character)

    suspend fun update(character: CharacterEntity) =
        dao.update(character)

    suspend fun delete(character: CharacterEntity) =
        dao.delete(character)

    suspend fun getCharacterById(id: Int) =
        dao.getCharacterById(id)


    suspend fun saveCharacter(character: CharacterEntity) {
        dao.insert(character)
    }
}
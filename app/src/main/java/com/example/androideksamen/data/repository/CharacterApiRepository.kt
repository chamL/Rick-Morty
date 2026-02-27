package com.example.androideksamen.data.repository

import com.example.androideksamen.data.api.RetrofitInstance
import com.example.androideksamen.data.model.Character

class CharacterApiRepository {

    suspend fun getCharacters(): List<Character> {
        return RetrofitInstance.api.getCharacters().results
    }
}
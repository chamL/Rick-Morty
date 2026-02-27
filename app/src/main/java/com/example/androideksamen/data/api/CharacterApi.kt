package com.example.androideksamen.data.api

import com.example.androideksamen.data.model.CharacterResponse
import retrofit2.http.GET

interface CharacterApi {

    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}
package com.example.androideksamen.data.model

data class CharacterResponse(
    val results: List<Character>
)
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,

    val origin: Map<String, String>,
    val location: Map<String, String>,
    val episode: List<String>
)
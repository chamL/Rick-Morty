package com.example.androideksamen.viewmodel

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.androideksamen.data.local.CharacterEntity
import com.example.androideksamen.data.local.DatabaseProvider
import com.example.androideksamen.data.model.Character
import com.example.androideksamen.data.repository.CharacterApiRepository
import com.example.androideksamen.data.repository.CharacterRepository
import kotlinx.coroutines.launch

/*
fetching characters from api
managing loading and error state
handling search and filter logic in main page
saving characters to local database
 */

enum class StatusFilter {
    ALL,
    ALIVE,
    DEAD,
    UNKNOWN
}

class CharacterViewModel(application: Application) :
    AndroidViewModel(application) {


    private val apiRepository = CharacterApiRepository()

    private val localRepository = CharacterRepository(
        DatabaseProvider.getDao(application)
    )

    // ui States
    var characters by mutableStateOf<List<Character>>(emptyList())
        private set

    var filteredCharacters by mutableStateOf<List<Character>>(emptyList())
        private set

    var selectedCharacter by mutableStateOf<Character?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var savedMessage by mutableStateOf<String?>(null)
        private set

    var searchQuery by mutableStateOf("")
        private set

    var selectedFilter by mutableStateOf(StatusFilter.ALL)
        private set


    init {
        fetchCharacters()
    }

    // public functions
    fun onSearchChange(query: String) {
        searchQuery = query
        applyFilters()
    }

    fun setFilter(filter: StatusFilter) {
        selectedFilter = filter
        applyFilters()
    }

    fun clearMessage() {
        savedMessage = null
    }

    fun saveCharacter(character: Character) {
        viewModelScope.launch {
            val entity = CharacterEntity(
                id = character.id,
                name = character.name,
                status = character.status,
                species = character.species,
                gender = character.gender,
                image = character.image
            )

            localRepository.insert(entity)
            savedMessage = "Character saved!"
        }
    }

    fun fetchCharacterById(id: Int) {
        viewModelScope.launch {
            isLoading = true

            try {
                if (characters.isEmpty()) {
                    characters = apiRepository.getCharacters()
                }

                selectedCharacter = characters.find { it.id == id }

            } catch (e: Exception) {
                errorMessage = "Failed to load character"
            }

            isLoading = false
        }
    }

    // private functions
    private fun fetchCharacters() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                characters = apiRepository.getCharacters()
                applyFilters()
            } catch (e: Exception) {
                errorMessage = "Failed to load characters"
            }

            isLoading = false
        }
    }

    private fun applyFilters() {
        filteredCharacters = characters.filter { character ->

            val matchesSearch =
                character.name.contains(searchQuery, ignoreCase = true)

            val matchesStatus =
                when (selectedFilter) {
                    StatusFilter.ALL -> true
                    StatusFilter.ALIVE ->
                        character.status.equals("Alive", true)
                    StatusFilter.DEAD ->
                        character.status.equals("Dead", true)
                    StatusFilter.UNKNOWN ->
                        character.status.equals("unknown", true)
                }

            matchesSearch && matchesStatus
        }
    }
}
package com.example.androideksamen.viewmodel

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.androideksamen.data.local.CharacterEntity
import com.example.androideksamen.data.local.DatabaseProvider
import com.example.androideksamen.data.repository.CharacterRepository
import kotlinx.coroutines.launch

/*
 loading a saved character from database
 updating character
 deleting character
 */

class EditCharacterViewModel(application: Application)
    : AndroidViewModel(application) {


    private val repository = CharacterRepository(
        DatabaseProvider.getDatabase(application).characterDao()
    )

    // state
    var character by mutableStateOf<CharacterEntity?>(null)
        private set

    var message by mutableStateOf<String?>(null)
        private set

    // public functions
    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            character = repository.getCharacterById(id)
        }
    }

    fun updateCharacter() {
        character?.let { current ->
            viewModelScope.launch {
                repository.update(current)
                message = "Character updated"
            }
        }
    }

    fun deleteCharacter() {
        character?.let { current ->
            viewModelScope.launch {
                repository.delete(current)
                message = "Character deleted"
            }
        }
    }

    // field update helpers
    fun updateName(newName: String) {
        character = character?.copy(name = newName)
    }

    fun updateStatus(newStatus: String) {
        character = character?.copy(status = newStatus)
    }

    fun updateSpecies(newSpecies: String) {
        character = character?.copy(species = newSpecies)
    }

    fun updateGender(newGender: String) {
        character = character?.copy(gender = newGender)
    }
}
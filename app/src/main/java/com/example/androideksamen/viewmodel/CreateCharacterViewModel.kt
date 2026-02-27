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
 managing input state for creating character
 validating input
 saving character to local database
 feedback with savedMessage
 */

class CreateCharacterViewModel(application: Application)
    : AndroidViewModel(application) {

    private val repository = CharacterRepository(
        DatabaseProvider.getDatabase(application).characterDao()
    )

// inputs states
    var name by mutableStateOf("")
    var status by mutableStateOf("")
    var species by mutableStateOf("")
    var gender by mutableStateOf("")
    var image by mutableStateOf("")

// feedback
    var savedMessage by mutableStateOf<String?>(null)
        private set

    // public functions
    fun saveCharacter() {

        // validation
        if (name.isBlank()) {
            savedMessage = "Name cannot be empty"
            return
        }

        viewModelScope.launch {

            repository.insert(
                CharacterEntity(
                    id = generateId(),
                    name = name,
                    status = status,
                    species = species,
                    gender = gender,
                    image = image
                )
            )

            savedMessage = "Character saved"
            clearFields()
        }
    }

    fun clearMessage() {
        savedMessage = null
    }

    // private helpers
    private fun clearFields() {
        name = ""
        status = ""
        species = ""
        gender = ""
        image = ""
    }

    private fun generateId(): Int {
        return System.currentTimeMillis().toInt()
    }
}
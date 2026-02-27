package com.example.androideksamen.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.androideksamen.data.local.DatabaseProvider
import com.example.androideksamen.data.local.CharacterEntity
import com.example.androideksamen.data.repository.CharacterRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SavedCharactersViewModel(application: Application)
    : AndroidViewModel(application) {

    fun deleteCharacter(character: CharacterEntity) {
        viewModelScope.launch {
            repository.delete(character)
            message = "Character removed!"
        }
    }

    var message by mutableStateOf<String?>(null)
        private set

    fun clearMessage() {
        message = null
    }

    private val dao =
        DatabaseProvider.getDatabase(application).characterDao()

    private val repository = CharacterRepository(dao)

    val characters =
        repository.getSavedCharacters()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList<CharacterEntity>()
            )
}
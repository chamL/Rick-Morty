package com.example.androideksamen.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Update
    suspend fun update(character: CharacterEntity)

    @Delete
    suspend fun delete(character: CharacterEntity)

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterEntity)

}
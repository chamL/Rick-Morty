package com.example.androideksamen.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    private var INSTANCE: Database? = null

    fun getDatabase(context: Context): Database {
        return INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context,
                Database::class.java,
                "character_db"
            ).build().also {
                INSTANCE = it
            }
        }
    }

    fun getDao(context: Context): CharacterDao {
        return getDatabase(context).characterDao()
    }
}
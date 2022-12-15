package com.valtergabriel.easypassword.src.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PassEntity::class], version = 2)
abstract class DatabaseManager : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "DATABASE_PASSWORD"
        private var INSTANCE: DatabaseManager? = null

        private fun createDatabase(context: Context): DatabaseManager {
            return Room.databaseBuilder(context, DatabaseManager::class.java, DATABASE_NAME).allowMainThreadQueries().build()
        }

        fun getInstance(context: Context): DatabaseManager {
            return (INSTANCE ?: createDatabase(context).also {
                INSTANCE = it
            })
        }
    }

    abstract fun getDao(): PassDAO


}
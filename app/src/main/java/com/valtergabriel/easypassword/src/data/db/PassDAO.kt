package com.valtergabriel.easypassword.src.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PassDAO {

    @Insert
    fun insertNewItem(passEntity: PassEntity)

    @Query("SELECT * FROM PassTable")
    fun getAllItems():List<PassEntity>

    @Query("SELECT * FROM PassTable WHERE id = :id")
    fun getItemById(id:Long):PassEntity

}
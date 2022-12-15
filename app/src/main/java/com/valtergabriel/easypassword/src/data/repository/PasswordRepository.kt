package com.valtergabriel.easypassword.src.data.repository

import com.valtergabriel.easypassword.src.domain.model.PasswordModel

interface PasswordRepository {
    suspend fun insertNewItem(passwordModel: PasswordModel)
    suspend fun getAllItems(): List<PasswordModel>
    suspend fun getItemById(id: Long): PasswordModel?


}
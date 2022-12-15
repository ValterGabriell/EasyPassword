package com.valtergabriel.easypassword.src.data.repository

import android.content.Context
import com.valtergabriel.easypassword.src.data.db.DatabaseManager
import com.valtergabriel.easypassword.src.data.db.PassEntity
import com.valtergabriel.easypassword.src.domain.model.PasswordModel

class MyRepositoryImpl(context: Context) : PasswordRepository {
    private val passDAO = DatabaseManager.getInstance(context).getDao()
    override suspend fun insertNewItem(passwordModel: PasswordModel) {
        passDAO.insertNewItem(PassEntity.fromModelToEntity(passwordModel))

    }

    override suspend fun getAllItems(): List<PasswordModel> {
        return passDAO.getAllItems().map { it.fromEntityToModel() }
    }

    override suspend fun getItemById(id: Long): PasswordModel {
        return passDAO.getItemById(id).fromEntityToModel()
    }


}
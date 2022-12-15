package com.valtergabriel.easypassword.src.data.db

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.valtergabriel.easypassword.src.domain.model.PasswordModel

@Entity(tableName = "PassTable")
data class PassEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,
    @ColumnInfo(name = "place")
    var place: String = "",
    @ColumnInfo(name = "password")
    var password: String = "",
    @ColumnInfo(name = "password_encoded")
    var passwordEncoded: String = ""
) {
    companion object {
        fun fromModelToEntity(passwordModel: PasswordModel) = PassEntity(
            passwordModel.id,
            passwordModel.place,
            passwordModel.password,
            passwordModel.passwordEncoded
        )
    }

    fun fromEntityToModel() = PasswordModel(id, place, password, passwordEncoded)
}
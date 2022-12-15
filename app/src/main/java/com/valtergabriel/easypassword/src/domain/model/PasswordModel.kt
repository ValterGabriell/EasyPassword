package com.valtergabriel.easypassword.src.domain.model

data class PasswordModel(
    var id: Long = 0L,
    var place: String = "",
    var password: String = "",
    var passwordEncoded: String = ""
)
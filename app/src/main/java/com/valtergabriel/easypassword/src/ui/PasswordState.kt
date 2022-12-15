package com.valtergabriel.easypassword.src.ui

import com.valtergabriel.easypassword.src.domain.model.PasswordModel

data class PasswordState(
    var isLoading: Boolean = false,
    val password:PasswordModel? = null,
    val error: String = ""
)
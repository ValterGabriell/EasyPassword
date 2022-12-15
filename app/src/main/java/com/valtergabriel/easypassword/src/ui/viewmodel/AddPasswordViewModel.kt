package com.valtergabriel.easypassword.src.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valtergabriel.easypassword.src.data.repository.MyRepositoryImpl
import com.valtergabriel.easypassword.src.domain.encrypt.Encrypty
import com.valtergabriel.easypassword.src.domain.model.PasswordModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class AddPasswordViewModel(private val myRepositoryImpl: MyRepositoryImpl) : ViewModel() {

    private var passwordModel = MutableStateFlow(PasswordModel())
    private var passwordToEncode = MutableStateFlow(String())

    fun savePassword(password: String, place: String, type: String): Boolean {
        if (password.isNotEmpty() && place.isNotEmpty() && type.isNotEmpty()) {
            //here we are encryting by the type choose by user
            checkTypeAndEncrypt(type, password).also {
                //here we are building the PasswordModel
                passwordModel.value.apply {
                    this.password = "$password|$type"
                    this.passwordEncoded = passwordToEncode.value
                    this.place = place
                }.also {
                    viewModelScope.launch {
                        myRepositoryImpl.insertNewItem(passwordModel.value).also {
                            println("saved ${passwordModel.value}")
                        }
                    }
                }
            }
            return true
        } else {
            return false
        }
    }

    private fun checkTypeAndEncrypt(type: String, password: String) {
        when (type) {
            "MD5" -> {
                passwordToEncode.value = Encrypty.MD5(password)
            }
            "SHA-1" -> {
                passwordToEncode.value = Encrypty.SHA1(password)
            }
            "SHA256" -> {
                passwordToEncode.value =  Encrypty.SHA256(password)
            }
        }
    }

}
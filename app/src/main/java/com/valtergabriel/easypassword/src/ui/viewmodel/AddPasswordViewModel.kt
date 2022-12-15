package com.valtergabriel.easypassword.src.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valtergabriel.easypassword.src.data.repository.MyRepositoryImpl
import com.valtergabriel.easypassword.src.domain.model.PasswordModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch



class AddPasswordViewModel(private val myRepositoryImpl: MyRepositoryImpl) : ViewModel() {

    private var passwordModel = MutableStateFlow(PasswordModel())


    fun savePassword(password: String, place: String) {
            passwordModel.value.apply {
                this.password = password
                this.passwordEncoded = password + "asd"
                this.place = place
            }.also {
                viewModelScope.launch {
                    myRepositoryImpl.insertNewItem(passwordModel.value).also {
                        println("saved ${passwordModel.value}")
                    }
                }
            }

    }
}
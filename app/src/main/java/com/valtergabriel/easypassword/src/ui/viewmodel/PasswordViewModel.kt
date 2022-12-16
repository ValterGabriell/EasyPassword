package com.valtergabriel.easypassword.src.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valtergabriel.easypassword.src.data.repository.MyRepositoryImpl
import com.valtergabriel.easypassword.src.domain.model.PasswordModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PasswordViewModel(private val myRepositoryImpl: MyRepositoryImpl) : ViewModel() {

    private val _item = MutableStateFlow(PasswordModel())
    val item = _item.asStateFlow()


    fun getItemById(id: Long) {
        viewModelScope.launch {
            val item = myRepositoryImpl.getItemById(id)
            _item.value = item
        }
    }

    fun deleteItemById(id: Long) {
        viewModelScope.launch {
            myRepositoryImpl.deleteItemById(id)
        }
    }
}
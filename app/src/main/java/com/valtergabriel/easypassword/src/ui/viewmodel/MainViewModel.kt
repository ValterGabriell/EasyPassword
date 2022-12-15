package com.valtergabriel.easypassword.src.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valtergabriel.easypassword.src.data.repository.MyRepositoryImpl
import com.valtergabriel.easypassword.src.domain.model.PasswordModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val myRepositoryImpl: MyRepositoryImpl) : ViewModel() {

    private val _listOfItems = MutableStateFlow(emptyList<PasswordModel>())
    val listOfItems: StateFlow<List<PasswordModel>> = _listOfItems.asStateFlow()

    fun getAllPlants() {
        viewModelScope.launch {
            val list = myRepositoryImpl.getAllItems()
            _listOfItems.value = list
        }
    }


}
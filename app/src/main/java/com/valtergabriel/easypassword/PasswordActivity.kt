package com.valtergabriel.easypassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.valtergabriel.easypassword.databinding.ActivityMainBinding
import com.valtergabriel.easypassword.databinding.ActivityPasswordBinding
import com.valtergabriel.easypassword.src.ui.viewmodel.PasswordViewModel
import org.koin.android.ext.android.inject


class PasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordBinding
    private val viewModel by inject<PasswordViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.extras?.get("id")
        if (id != null) {
            getItemById(id as Long)
        } else {
            binding.txtPlace.text = "not found"
        }

    }


    fun getItemById(id: Long) {
        viewModel.getItemById(id).also {
            binding.txtPlace.text = viewModel.item.value.place
            binding.txtDesc.text = viewModel.item.value.passwordEncoded
        }

    }
}
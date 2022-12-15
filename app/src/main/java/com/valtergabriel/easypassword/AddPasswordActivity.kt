package com.valtergabriel.easypassword

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import com.valtergabriel.easypassword.databinding.ActivityAddPasswordBinding
import com.valtergabriel.easypassword.src.ui.viewmodel.AddPasswordViewModel
import org.koin.android.ext.android.inject


class AddPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPasswordBinding
    private val viewModel by inject<AddPasswordViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            saveNewPassword()
        }
    }

    private fun saveNewPassword() {
        val place = binding.editPlace.text.toString()
        val pass = binding.editPass.text.toString()

        if (place.isNotEmpty() && pass.isNotEmpty()) {
            viewModel.savePassword(pass, place)
        } else {
            Toast.makeText(
                this@AddPasswordActivity,
                "Preencha tudo corretamente",
                Toast.LENGTH_SHORT
            ).show()
        }


    }
}
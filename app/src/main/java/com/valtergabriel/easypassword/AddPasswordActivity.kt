package com.valtergabriel.easypassword

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import com.valtergabriel.easypassword.databinding.ActivityAddPasswordBinding
import com.valtergabriel.easypassword.src.ui.viewmodel.AddPasswordViewModel
import org.koin.android.ext.android.inject


class AddPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPasswordBinding
    private val viewModel by inject<AddPasswordViewModel>()
    private var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            val radioButton = radioGroup.findViewById<RadioButton>(i)
            val isChecked = radioButton.isChecked
            if (isChecked) {
                type = radioButton.text.toString()
            }
        }
        binding.btnSave.setOnClickListener {
            saveNewPassword()
        }
    }

    private fun saveNewPassword() {
        val place = binding.editPlace.text.toString()
        val pass = binding.editPass.text.toString()
        viewModel.savePassword(pass, place, type).also { isOk ->
            if (isOk) {
                Intent(this@AddPasswordActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            } else {
                Toast.makeText(
                    this@AddPasswordActivity,
                    "Um erro inesperado ocorreu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
package com.valtergabriel.easypassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.valtergabriel.easypassword.databinding.ActivityAddPasswordBinding
import com.valtergabriel.easypassword.databinding.ActivityMainBinding
import com.valtergabriel.easypassword.src.ui.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getAllItems()
        binding.btnMainAdd.setOnClickListener {
            Intent(this, AddPasswordActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    fun getAllItems() {
        viewModel.getAllPlants().also {
            val list = viewModel.listOfItems.value
            binding.txtList.text = "Senha de ${list[0].place}"

            binding.txtList.setOnClickListener {
                Intent(this, PasswordActivity::class.java).also { intent ->
                    intent.putExtra("id", list[0].id)
                    startActivity(intent)
                }
            }
        }

    }
}
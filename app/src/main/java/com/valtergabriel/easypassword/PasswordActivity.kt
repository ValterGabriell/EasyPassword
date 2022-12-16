package com.valtergabriel.easypassword

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.extras?.get("id")
        if (id != null) {
            getItemById(id as Long)
        } else {
            binding.txtPlace.text = "not found"
        }

        binding.txtDesc.setOnLongClickListener{
            copyText()
            return@setOnLongClickListener true
        }

    }


    fun getItemById(id: Long) {
        viewModel.getItemById(id).also {
            val type = viewModel.item.value.password.split("|").get(1)
            binding.txtType.text = type
            binding.txtPlace.text = viewModel.item.value.place
            binding.txtDesc.text = viewModel.item.value.passwordEncoded
        }
    }

    fun copyText() {
        val clipboard: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.text = binding.txtDesc.text.toString()
        Toast.makeText(applicationContext, "Senha copiada!", Toast.LENGTH_SHORT).show();
    }
}
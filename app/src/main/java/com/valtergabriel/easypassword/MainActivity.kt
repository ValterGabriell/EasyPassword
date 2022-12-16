package com.valtergabriel.easypassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.valtergabriel.easypassword.adapters.MyAdapter
import com.valtergabriel.easypassword.databinding.ActivityAddPasswordBinding
import com.valtergabriel.easypassword.databinding.ActivityMainBinding
import com.valtergabriel.easypassword.src.ui.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by inject<MainViewModel>()
    private lateinit var adapter: MyAdapter


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
            if (list.isNotEmpty()) {
                binding.rvMain.visibility = View.VISIBLE
                binding.textView5.visibility = View.GONE
                adapter = MyAdapter(list)
                binding.rvMain.adapter = adapter
                binding.rvMain.layoutManager = LinearLayoutManager(this)
                adapter.onItemClick = { id, _ ->
                    Intent(this, PasswordActivity::class.java).also { intent ->
                        intent.putExtra("id", id)
                        startActivity(intent)
                    }
                }
            }else{
                binding.rvMain.visibility = View.GONE
                binding.textView5.visibility = View.VISIBLE
            }

        }

    }
}
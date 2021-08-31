package com.example.projetomovieslocaliza.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.projetomovieslocaliza.databinding.LoginMoviesBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : LoginMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btlogin.setOnClickListener{
            val intent = Intent(this, StatusMoviesActivity::class.java)
            startActivity(intent)
        }
    }
}
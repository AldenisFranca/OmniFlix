package com.example.projetomovieslocaliza.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.projetomovieslocaliza.R


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        changeToLogin()
    }

    fun changeToLogin(){
        val intent = Intent(this, LoginActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            change(intent)
        }, 2000)
    }
    fun change(jump:Intent){
        startActivity(jump)
        finish()
    }

}
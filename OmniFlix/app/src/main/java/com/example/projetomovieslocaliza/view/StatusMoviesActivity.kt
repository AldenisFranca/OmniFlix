package com.example.projetomovieslocaliza.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projetomovieslocaliza.databinding.StatusMoviesBinding
import com.example.projetomovieslocaliza.model.MovieRepository
import com.example.projetomovieslocaliza.view.ListMoviesActivity.Companion.FAVORITES
import com.example.projetomovieslocaliza.view.ListMoviesActivity.Companion.POPULAR
import com.example.projetomovieslocaliza.view.ListMoviesActivity.Companion.TYPE_KEY
import com.example.projetomovieslocaliza.view.ListMoviesActivity.Companion.UPCOMING

class StatusMoviesActivity : AppCompatActivity() {
    private lateinit var binding: StatusMoviesBinding
    private lateinit var adapter: ListMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StatusMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = ListMoviesAdapter{ id ->
            val pulaTela = Intent(this, ListMoviesActivity::class.java)
            pulaTela.putExtra(TYPE_KEY, id)
            startActivity(pulaTela)
        }

        callPopular()

        binding.butFavorite.setOnClickListener { callFavorite() }
        binding.butPopular.setOnClickListener { callPopular() }


        binding.butPopular.setOnClickListener {
            val mudaTela = Intent(this, ListMoviesActivity::class.java)
            mudaTela.putExtra(TYPE_KEY, POPULAR)
            startActivity(mudaTela)
        }

        binding.butUpcoming.setOnClickListener {
            val mudaTela = Intent(this, ListMoviesActivity::class.java)
            mudaTela.putExtra(TYPE_KEY, UPCOMING)
            startActivity(mudaTela)
        }

        binding.butFavorite.setOnClickListener {
            val mudaTela = Intent(this, ListMoviesActivity::class.java)
            mudaTela.putExtra(TYPE_KEY, FAVORITES)
            startActivity(mudaTela)
        }
    }

    private fun callFavorite() {
        MovieRepository.getFavoritos(this) { list ->
            adapter.addFilmes(list)
            binding.butPopular.visibility = View.VISIBLE
            binding.butFavorite.visibility = View.VISIBLE
        }
    }

    private fun callPopular() {
        MovieRepository.getPopular { list ->
            adapter.addFilmes(list)
            binding.butFavorite.visibility = View.VISIBLE
            binding.butPopular.visibility = View.VISIBLE
        }
    }
}
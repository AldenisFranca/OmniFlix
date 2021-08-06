package com.example.projetomovieslocaliza.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.projetomovieslocaliza.databinding.ActivityListMoviesBinding
import com.example.projetomovieslocaliza.model.MovieRepository
import com.example.projetomovieslocaliza.view.DetailsActivity.Companion.EXTRA_ID

class ListMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListMoviesBinding
    private var nextPage: Int = 1
    private lateinit var adapter: ListMoviesAdapter
    private var tipo: Int = -1

    companion object {
        const val UPCOMING = 0
        const val POPULAR = 1
        const val FAVORITES = 2
        const val TYPE_KEY = "com.example.projetomovieslocaliza.view.StatusMovies.TYPE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        statusList()
        binding.verMais.setOnClickListener {
            getMovies()
        }

    }

    private fun statusList() {
        adapter = ListMoviesAdapter { id ->
            openDetailsActivity(id)
        }
        binding.poster.adapter = adapter
        tipo = intent.getIntExtra(TYPE_KEY, -1)
        getMovies()
    }

    private fun getMovies() {
        binding.verMais.visibility = View.GONE
        when (tipo) {
            UPCOMING -> {
                getUpcoming()
            }
            POPULAR -> {
                getPopular()
            }
            FAVORITES -> {
                getFavoritos()
            }
            else -> {
                Toast.makeText(this, "ERRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getFavoritos() {
        MovieRepository.getFavoritos(this) { list ->
            list.forEach { movie ->
                movie.isFavorite = true
            }
            println(list)
            adapter.addFilmes(list)
            binding.verMais.visibility = View.VISIBLE
            addPage()
        }
    }

    private fun getPopular() {
        MovieRepository.getPopular(nextPage) { list ->
            adapter.addFilmes(list)
            binding.verMais.visibility = View.VISIBLE
            addPage()
        }
    }

    private fun addPage() {
        nextPage++
    }

    private fun getUpcoming() {
        MovieRepository.getUpcoming(nextPage) { list ->
            adapter.addFilmes(list)
            binding.verMais.visibility = View.VISIBLE
            addPage()
        }
    }

    private fun initLayout() {
        this.binding = ActivityListMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun openDetailsActivity(id: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}
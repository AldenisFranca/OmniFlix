package com.example.projetomovieslocaliza.view

import android.content.Intent
import android.graphics.Movie
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projetomovieslocaliza.databinding.DetailsMovieBinding
import com.example.projetomovieslocaliza.model.MovieModel
import com.example.projetomovieslocaliza.model.MovieRepository

class DetailsActivity : AppCompatActivity(){
    companion object {
        const val EXTRA_ID = "com.example.projetomovieslocaliza.view.DetailsActivity.EXTRA_ID"
    }

    private lateinit var binding: DetailsMovieBinding
    private lateinit var adapter: ListMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idApi = intent.getIntExtra(EXTRA_ID, -1)


        MovieRepository.getMovie({
            val valueStar = it.vote_average / 2
            if (binding.butStar.isChecked) MovieRepository.addFavoritos(this, it)
            else MovieRepository.deleteFavoritos(this, it)
            binding.tituloTv.text = it.title
            binding.sinopseTv.text = it.overview
            binding.duration.text = "${it.runtime} min"
            binding.ano.text = it.release_date.take(4)
            binding.genero2.text = getFormattedGenreList(it)
            binding.age.text = if (it.adult) "+18" else "-18"
            binding.company.text = getFormattedCompanyList(it)
            binding.value.text = valueStar.toString()
            it.vote_average.let { rate ->
                binding.stars.rating = (rate / 2) }

            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${it.backdrop_path}")
                .into(binding.poster)
        }, idApi)

        binding.butAssistir.setOnClickListener {
            val intent = Intent(this, ListMoviesActivity::class.java)
            startActivity(intent)
        }
    }

//    fun getUrlFromIntent(view: View) {
//        val url = "https://www.google.com/search?q=${binding.tituloTv.text}"
//        val openURL = Intent(Intent.ACTION_VIEW)
//        openURL.data = Uri.parse(url)
//        startActivity(openURL)
//    }

    fun getFormattedGenreList(movie: MovieModel): String{
        var textGenre = ""
        movie.genres?.forEachIndexed {index, genre ->
            if (index == 0) {
                textGenre += genre
            }
            else{
                textGenre += ", $genre"
            }
        }
        return textGenre
    }

    private fun getFormattedCompanyList(movie: MovieModel): String{
        var textCompany = ""
        movie.production_companies?.forEachIndexed{index, company ->

            if(index == 0) {
                textCompany += "$company "
            }
            else{
                textCompany += ", $company"
            }
        }
        return textCompany
    }

    fun inFavorite(view: View) {
        if (binding.butStar.isChecked) {
            Toast.makeText(this, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Removido dos Favoritos", Toast.LENGTH_SHORT).show()
        }
    }

}
package com.example.projetomovieslocaliza.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import androidx.room.RoomDatabase
import com.bumptech.glide.Glide
import com.example.projetomovieslocaliza.databinding.ListMoviesItemsBinding
import com.example.projetomovieslocaliza.model.MovieModel
import com.example.projetomovieslocaliza.model.MovieModelDao

class MoviesViewHolder(val binding: ListMoviesItemsBinding) : RecyclerView.ViewHolder(binding.root)

class ListMoviesAdapter(val movieClickListener: (Int) -> Unit) : RecyclerView.Adapter<MoviesViewHolder>() {

    // mudar aqui caso queira objeto e capturar não apenas texto <Object>
    val listaFilmes: MutableList<MovieModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListMoviesItemsBinding.inflate(inflater, parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = listaFilmes[position]
        Glide.with(holder.binding.root)
            .load("https://image.tmdb.org/t/p/w500${item.poster_path}")
            .into(holder.binding.capaFilme)
        holder.binding.capaFilme.setOnClickListener{ movieClickListener(item.id) }
    }

    override fun getItemCount(): Int {
        return listaFilmes.size
    }

    fun addFilmes(list: List<MovieModel>) {
        listaFilmes.clear()
        listaFilmes.addAll(list)
        notifyDataSetChanged()
    }

    @Database(entities = arrayOf(MovieModel::class), version = 1)
    abstract class AppDataBase: RoomDatabase() {
        abstract fun movieDao(): MovieModelDao
    }
}
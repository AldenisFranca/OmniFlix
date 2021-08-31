package com.example.projetomovieslocaliza.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieModelDao {
    @Insert
    fun insertFavorite(movie: MovieModel)

    @Delete
    fun deleteFavorite(movie: MovieModel)

    @Query("SELECT * FROM Favorites")
    fun getAllFavorite() :List<MovieModel>

    @Query("SELECT * FROM Favorites WHERE :idMovie = id LIMIT 1")
    fun getFavoriteById(idMovie: Int) : MovieModel?
}
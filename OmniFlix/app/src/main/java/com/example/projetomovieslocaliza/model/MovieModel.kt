package com.example.projetomovieslocaliza.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Favorites")
data class MovieModel(
    @ColumnInfo(name = "title")
    val title: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "poster_path")
    val poster_path: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String,
    @ColumnInfo(name = "runtime")
    val runtime: String,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "vote_average")
    val vote_average: Float,
    @Ignore
    val genres: List<GenreModel> = listOf(),
    @Ignore
    val production_companies: List<CompanyModel> = listOf(),
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false)
{
    constructor(title: String,id: Int,poster_path: String,overview: String,backdrop_path: String,runtime: String,
                release_date: String,adult: Boolean, vote_average: Float, isFavorite: Boolean) :
            this(title, id, poster_path, overview, backdrop_path,
        runtime, release_date, adult,vote_average, listOf(), listOf(), isFavorite)
}


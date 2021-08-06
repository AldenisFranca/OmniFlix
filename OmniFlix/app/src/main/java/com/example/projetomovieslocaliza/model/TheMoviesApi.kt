package com.example.projetomovieslocaliza.model

import com.example.projetomovieslocaliza.model.ApiConsts.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMoviesApi {
    @GET("3/movie/popular")
    fun listPopular(@Query("api_key") apiKey: String = API_KEY,
                    @Query("language") idioma: String = "pt-BR",
                    @Query("page") pagina: Int = 1): Call<MovieList>

    @GET("3/movie/{idMovieURL}")
    fun getMovieById(@Path("idMovieURL")id:Int,
                     @Query("api_key") apiKey: String = API_KEY,
                     @Query("language") language: String = "pt-BR",
                     ): Call<MovieModel>

    @GET("3/movie/upcoming")
    fun listUpcoming(@Query("api_key") apiKey: String = API_KEY,
                    @Query("language") idioma: String = "pt-BR",
                    @Query("page") pagina: Int = 1): Call<MovieList>

    @GET("latest")
    fun listFavorite(@Query("api_key") apiKey: String = API_KEY,
                     @Query("language") idiom: String = "pt-BR",
                     @Query("page") page: Int): Call<MovieList>

}


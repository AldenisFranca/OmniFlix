package com.example.projetomovieslocaliza.model

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.themoviedb.org/")
        .build()

    val moviesApi: TheMoviesApi = retrofit.create(TheMoviesApi::class.java)
    var dataBase: AppDataBase? = null

    fun initDataBase(contex: Context) {
        if (dataBase == null) {
            dataBase = Room.databaseBuilder(contex, AppDataBase::class.java, "database_app").build()
        }
    }

    fun addFavoritos(contex: Context, movieModel: MovieModel) {
        initDataBase(contex)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                dataBase?.movieDao()?.insertFavorite(movieModel)
            }
        }
    }

    fun getFavoritos(contex: Context, callback: (List<MovieModel>) -> Unit) {
        initDataBase(contex)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                val listFavorites = dataBase?.movieDao()?.getAllFavorite()
                withContext(Dispatchers.Main) {
                    callback(listFavorites ?: listOf())
                }
            }
        }
    }

    fun getFavoriteById(contex: Context, id: Int ,callback: (MovieModel?) -> Unit) {
        initDataBase(contex)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                val listFavorites = dataBase?.movieDao()?.getFavoriteById(idMovie = id)
                withContext(Dispatchers.Main) {
                    callback(listFavorites)
                }
            }
        }
    }



    fun deleteFavoritos(contex: Context, movieModel: MovieModel) {
        initDataBase(contex)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                dataBase?.movieDao()?.deleteFavorite(movieModel)
            }

        }
    }

    fun getPopular(pagina: Int = 1, callback: (List<MovieModel>) -> Unit) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = moviesApi.listPopular(pagina = pagina)
                callApi.enqueue(object : Callback<MovieList> {
                    override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                        callback(response.body()?.results ?: mutableListOf())
                    }

                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    }
                })
            }
        }
    }

    fun getUpcoming(pagina: Int = 1, callback: (List<MovieModel>) -> Unit) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = moviesApi.listUpcoming(pagina = pagina)
                callApi.enqueue(object : Callback<MovieList> {
                    override fun onResponse(
                        call: Call<MovieList>, response: Response<MovieList>) {
                        callback(response.body()?.results ?: mutableListOf())
                    }

                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    }
                })
            }
        }
    }

    fun getMovie(callback: (MovieModel) -> Unit, id: Int) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = moviesApi.getMovieById(id)
                callApi.enqueue(object : Callback<MovieModel> {
                    override fun onResponse(
                        call: Call<MovieModel>,
                        response: Response<MovieModel>
                    ) {
                        response.body()?.let { movie ->
                            callback(movie)
                        }
                    }
                    override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                    }
                })
            }
        }
    }
}
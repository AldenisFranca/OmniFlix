package com.example.projetomovieslocaliza.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MovieModel::class), version = 1)
abstract class AppDataBase:RoomDatabase() {
    abstract fun movieDao(): MovieModelDao
}
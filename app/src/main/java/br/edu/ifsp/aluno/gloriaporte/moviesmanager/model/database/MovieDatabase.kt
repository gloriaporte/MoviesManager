package br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.dao.MovieDAO
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun MovieDAO(): MovieDAO

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase?=null
        const val MOVIE_DATABASE = "MovieDatabase"

        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "database.bd"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
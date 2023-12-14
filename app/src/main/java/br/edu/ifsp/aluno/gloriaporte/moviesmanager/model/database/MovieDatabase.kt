package br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.dao.MovieDAO
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDAO

        companion object {
            const val MOVIE_DATABASE = "movieDatabase"
        }

//    companion object {
//        const val MOVIE_DATABASE = "moviesManagerDatabase"
//
//        @Volatile
//        private var INSTANCE: MovieDatabase? = null
//
//        fun getDatabase(context: Context): MovieDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    MovieDatabase::class.java,
//                    "movie_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}
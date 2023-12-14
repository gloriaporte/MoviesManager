package br.edu.ifsp.aluno.gloriaporte.moviesmanager.controller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.database.MovieDatabase
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application): AndroidViewModel(application) {
    private val movieDaoImpl = Room.databaseBuilder(
        application.applicationContext,
        MovieDatabase::class.java,
        MovieDatabase.MOVIE_DATABASE
    ).build().movieDao()

    //Lista dos filmes
    val allMovies = MutableLiveData<MutableList<Movie>>()

    //Filme
    lateinit var movie: Movie

    fun insertMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDaoImpl.insert(movie)
        }
    }

    fun deleteMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDaoImpl.delete(movie)
        }
    }

    fun editMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDaoImpl.update(movie)
        }
    }

    fun getMovie(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            movie = movieDaoImpl.getMovieByName(name)
        }
    }

    fun getMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val movies = movieDaoImpl.getAllMovies()
            allMovies.postValue(movies)
        }
    }

    fun getMoviesOrderByName() {
        CoroutineScope(Dispatchers.IO).launch {
            val movies = movieDaoImpl.getAllMoviesOrderByName()
            allMovies.postValue(movies)
        }
    }

    fun getMoviesOrderByStars() {
        CoroutineScope(Dispatchers.IO).launch {
            val movies = movieDaoImpl.getAllMoviesOrderByStars()
            allMovies.postValue(movies)
        }
    }


}
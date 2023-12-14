package br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Movie

@Dao
interface MovieDAO {

    @Insert
    fun insert(movie: Movie)

    @Update
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    //Selecionar todos
    @Query("SELECT * FROM movie")
    fun getAllMovies(): MutableList<Movie>

    //Selecionar apenas por nome
    @Query("SELECT * FROM movie WHERE name = :name")
    fun getMovieByName(name: String): Movie

    //Ordenar por nome
    @Query("SELECT * FROM movie ORDER BY name")
    fun getAllMoviesOrderByName(): MutableList<Movie>

    //Ordenar por nota
    @Query("SELECT * FROM movie ORDER BY stars")
    fun getAllMoviesOrderByStars(): MutableList<Movie>
}
package br.edu.ifsp.aluno.gloriaporte.moviesmanager.controller

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.room.Room
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.database.MovieDatabase

class MovieViewModel(application: Application): ViewModel() {
    private val movieDaoImpl = Room.databaseBuilder(
        application.applicationContext,
        MovieDatabase::class.java,
        MovieDatabase.INSTANCE
    )
}
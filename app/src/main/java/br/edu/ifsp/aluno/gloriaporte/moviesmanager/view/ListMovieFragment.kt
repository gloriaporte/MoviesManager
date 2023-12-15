package br.edu.ifsp.aluno.gloriaporte.moviesmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.R
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.controller.MovieViewModel
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.databinding.FragmentListMovieBinding
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Movie

class ListMovieFragment : Fragment() {

    private lateinit var  fragmentListMovieBinding: FragmentListMovieBinding
    private val movieList: MutableList<Movie> = mutableListOf()

    private val viewModel: MovieViewModel by viewModels {
        MovieViewModel.MovieViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_movie, container, false)
    }

}
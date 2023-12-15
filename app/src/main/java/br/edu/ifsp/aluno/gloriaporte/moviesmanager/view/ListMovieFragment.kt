package br.edu.ifsp.aluno.gloriaporte.moviesmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.R
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.controller.MovieViewModel
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.controller.MovieViewModelFactory
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.databinding.FragmentListMovieBinding
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Movie
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.view.adapter.MovieAdapter

class ListMovieFragment : Fragment() {

    private lateinit var  fragmentListMovieBinding: FragmentListMovieBinding
    private val movieList: MutableList<Movie> = mutableListOf()

    private val viewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(requireActivity().application)
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(movieList)
    }

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentListMovieBinding = FragmentListMovieBinding.inflate(inflater, container, false)

        fragmentListMovieBinding.apply {
            recyclerview.layoutManager = LinearLayoutManager(context)
            recyclerview.adapter = movieAdapter
            (activity as? AppCompatActivity)?.supportActionBar?.subtitle = getString(R.string.movie_list)
        }

        return fragmentListMovieBinding.root
    }

}
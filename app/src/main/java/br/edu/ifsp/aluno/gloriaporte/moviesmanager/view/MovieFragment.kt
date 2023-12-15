package br.edu.ifsp.aluno.gloriaporte.moviesmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.R
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.controller.MovieViewModel
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.databinding.FragmentMovieBinding
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Gender
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Movie
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Type
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.view.adapter.GenderAdapter
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.view.adapter.TypeAdapter
import com.google.android.material.snackbar.Snackbar
import java.lang.Thread.sleep

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieDetailsBinding: FragmentMovieBinding

    private lateinit var viewModel: MovieViewModel

    lateinit var movie: Movie

    private lateinit var nameEditText: EditText
    private lateinit var releaseYearsEditText: EditText
    private lateinit var productionEditText: EditText
    private lateinit var minutesEditText: EditText
    private lateinit var watchedEditText: CheckBox
    private lateinit var starsEditText: EditText
    private lateinit var genreEditText: Spinner
    private lateinit var typeEditText: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieDetailsBinding = FragmentMovieBinding.inflate(inflater, container, false)

        fragmentMovieDetailsBinding.apply {
            (activity as? AppCompatActivity)?.supportActionBar?.subtitle = getString(R.string.details_movie_view)
            fragmentMovieDetailsBinding.commonLayout.nameET.isEnabled = false
            movieDetailsView(false)
        }

        return fragmentMovieDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        layoutFieldInitializer()

        val nameMovie = requireArguments().getString("nameMovie")

        if (nameMovie != null) {
            viewModel.getMovie(nameMovie)
            sleep(200)
            this.movie = viewModel.movie

            nameEditText.setText(movie.name)
            releaseYearsEditText.setText(movie.releasedYear)
            productionEditText.setText(movie.production)
            minutesEditText.setText(movie.minutes.toString())
            watchedEditText.isChecked = isWatchedChecked()
            starsEditText.setText(if (movie.stars !== null) movie.stars.toString() else "")
        }

        inicializeMenu()
    }


    private fun layoutFieldInitializer() {
        this.nameEditText = fragmentMovieDetailsBinding.commonLayout.nameET
        this.releaseYearsEditText = fragmentMovieDetailsBinding.commonLayout.releasedYearET
        this.productionEditText = fragmentMovieDetailsBinding.commonLayout.productionET
        this.minutesEditText = fragmentMovieDetailsBinding.commonLayout.minutesET
        this.watchedEditText = fragmentMovieDetailsBinding.commonLayout.watchedCB
        this.genreEditText = fragmentMovieDetailsBinding.commonLayout.genderSP
        this.typeEditText = fragmentMovieDetailsBinding.commonLayout.typeSP
    }

    private fun movieDetailsView(disabled: Boolean) {
        fragmentMovieDetailsBinding.commonLayout.saveBt.visibility = if (disabled) View.VISIBLE  else View.GONE
        fragmentMovieDetailsBinding.commonLayout.releasedYearET.isEnabled = disabled
        fragmentMovieDetailsBinding.commonLayout.productionET.isEnabled = disabled
        fragmentMovieDetailsBinding.commonLayout.minutesET.isEnabled = disabled
        fragmentMovieDetailsBinding.commonLayout.watchedCB.isEnabled = disabled
        fragmentMovieDetailsBinding.commonLayout.starsRB.isEnabled = disabled
        fragmentMovieDetailsBinding.commonLayout.genderSP.isEnabled = disabled
        fragmentMovieDetailsBinding.commonLayout.typeSP.isEnabled = disabled


    }

    private fun isWatchedChecked(): Boolean {
        return movie.watched
    }


    private fun inicializeMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.details_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_deleteMovie -> {
                        viewModel.deleteMovie(viewModel.movie)
                        Snackbar.make(
                            fragmentMovieDetailsBinding.root,
                            "Filme excluido",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        findNavController().popBackStack()
                        true
                    }

                    R.id.action_updateMovie -> {
                        movieDetailsView(true)
                        fragmentMovieDetailsBinding.commonLayout.saveBt.setOnClickListener {
                            //Nao implementado
                        }
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}
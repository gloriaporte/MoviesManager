package br.edu.ifsp.aluno.gloriaporte.moviesmanager.view.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.R
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.databinding.TileMovieBinding
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Movie

class MovieAdapter(
    private val movieList: MutableList<Movie>,
    private val onMovieClickListener: OnMovieClickListener
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    lateinit var movieListener: OnMovieClickListener
    private lateinit var tileMovieBinding: TileMovieBinding

    inner class MovieViewHolder(view: TileMovieBinding): RecyclerView.ViewHolder(view.root) {
        var nameVH = view.nameTv
        var watchedVH = view.watchedTv
        var starsVH = view.starsTv
        var genreVH = view.genreTv
        var imgVH = view.imgIm


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}
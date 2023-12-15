package br.edu.ifsp.aluno.gloriaporte.moviesmanager.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.databinding.TileMovieBinding
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Movie

class MovieAdapter(private val movieList: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    lateinit var movieListener: OnMovieClickListener
    private lateinit var tileMovieBinding: TileMovieBinding
    val movieAdapter = MovieAdapter(movieList)

    inner class MovieViewHolder(view: TileMovieBinding): RecyclerView.ViewHolder(view.root) {
        var nameVH = view.nameTv
        var watchedVH = view.watchedTv
        var starsVH = view.starsTv
        var genreVH = view.genreTv
        var typeVH = view.typeTv

        init {
            view.root.setOnClickListener {
                movieListener?.onMovieClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        tileMovieBinding = TileMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return  MovieViewHolder(tileMovieBinding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.nameVH.text = movieList[position].name
        holder.watchedVH.text = setWatched(movieList[position].watched)
        holder.starsVH.text = movieList[position].stars?.let { setStars(it) }
        holder.genreVH.text = movieList[position].genre.toString()
        holder.typeVH.text = movieList[position].type.toString()
//
//        val byteArray = imageList[position]
//        if (byteArray != null) {
//            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
//            Glide.with(context).load(bitmap).into(holder.imgUrlViewHolder)
//        } else {
//            Glide.with(context).load(R.drawable.placeholder_image).into(holder.imgUrlViewHolder)
//        }
    }

    fun setClickListener(listener: MovieListener)
    {
        this.movieListener = listener
    }

    interface MovieListener
        : OnMovieClickListener {
        fun onItemClick(pos: Int)
        override fun onMovieClick(position: Int) {
            TODO("Not yet implemented")
        }

        override fun onRemoveMovieMenuItemClick(position: Int) {
            TODO("Not yet implemented")
        }

        override fun onEditMovieMenuItemClick(position: Int) {
            TODO("Not yet implemented")
        }

        override fun onWatchCheckboxClick(position: Int, watched: Boolean) {
            TODO("Not yet implemented")
        }
    }
    private fun setStars(stars: Int): String
    {
        var star = ""
        for (x in 0 until stars){
            star += "⭐"
        }
        return star
    }

    private fun setWatched(watched: Boolean): String
    {
        if (watched) return "Assisti" else return "Não assisti"
    }

}
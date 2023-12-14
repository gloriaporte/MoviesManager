package br.edu.ifsp.aluno.gloriaporte.moviesmanager.view.adapter

interface OnMovieClickListener {
    fun onMovieClick(position: Int)

    fun onRemoveMovieMenuItemClick(position: Int)

    fun onEditMovieMenuItemClick(position: Int)

    fun onWatchCheckboxClick(position: Int, watched: Boolean)

}
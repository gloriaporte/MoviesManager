package br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity

import br.edu.ifsp.aluno.gloriaporte.moviesmanager.R

enum class Gender (
    private val description: String,
    val icon: Int = R.drawable.baseline_list_24
) {
    ROMANCE (
        "Romance",
        R.mipmap.ic_heart
    ),
    HORROR (
        "Terror",
        //icone
    ),
    TRASH (
        "Trash",
        //icone
    ),
    THRILLER (
        "Suspense",
        //icone
    ),
    COMEDY (
        "Comédia",
        //icone
    ),
    SLASHER (
        "Slasher",
        //icone
    ),
    FOUND_FOOTAGE (
        "Found Footage",
        R.drawable.baseline_videocam_24
    ),
    DRAMA (
        "Drama",
        //icone
    )
}
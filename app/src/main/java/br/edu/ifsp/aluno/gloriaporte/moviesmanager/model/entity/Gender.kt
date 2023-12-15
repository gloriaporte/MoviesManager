package br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity

import br.edu.ifsp.aluno.gloriaporte.moviesmanager.R

enum class Gender (
    val description: String,
    val icon: Int = R.drawable.baseline_list_24
) {
    ROMANCE (
        "Romance",
        R.mipmap.ic_heart
    ),
    HORROR (
        "Terror",
        R.mipmap.ic_horror
    ),
    THRILLER (
        "Suspense",
        R.mipmap.ic_thriller
    ),
    COMEDY (
        "Comédia",
        R.mipmap.ic_comedia
    ),
    SLASHER (
        "Slasher",
        R.mipmap.ic_slasher
    ),
    FOUND_FOOTAGE (
        "Found Footage",
         R.mipmap.ic_foundfootage
    ),
    DRAMA (
        "Drama",
        R.mipmap.ic_drama
    )
}
package br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.R

enum class Type (
    private val description: String,
    val icon: Int = R.drawable.baseline_list_24
) {
    VHS (
        "VHS",
        R.mipmap.ic_heart
    ),
    DVD (
        "DVD",
        //icone
    )
}
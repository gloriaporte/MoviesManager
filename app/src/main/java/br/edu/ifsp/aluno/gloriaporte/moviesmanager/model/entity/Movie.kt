package br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movie (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val production: String,
    val minutes: Long,
    val watched: Boolean = false,
    val stars: Int? = null,
    val genre: String,
    val imagePath: String,
    val type: String,
    val releasedYear: String
)
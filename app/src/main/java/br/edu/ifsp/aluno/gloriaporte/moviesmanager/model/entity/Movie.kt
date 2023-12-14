package br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movie (
    @PrimaryKey(autoGenerate = true)
    val name:String,
    val production: String,
    val minutes: Long,
    val watched: Int,
    val stars: Int,
    val genre: String,
    val imagePath: String
)
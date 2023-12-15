package br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val production: String,
    val minutes: Long,
    val watched: Boolean = false,
    val stars: Int? = null,
    val genre: Gender,
    val type: Type,
    val releasedYear: String,
    val image: ByteArray?
    )
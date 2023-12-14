package br.edu.ifsp.aluno.gloriaporte.moviesmanager.view.adapter

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.databinding.StarsBinding
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Movie

class StarsAdapter(
    context: Context,
    private val movie: Movie,
    private val sendRatingValue: (Movie, Int) -> Unit
) : Dialog(context) {
    private val binding: StarsBinding by lazy {
        StarsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupButtons()
    }

    private fun setupButtons() {
        binding.apply {
            btCancel.setOnClickListener { dismiss() }
            btSend.setOnClickListener {
                sendRatingValue(movie, (ratingBar.rating * 2).toInt())
                dismiss()
            }
        }
    }
}
@file:OptIn(ExperimentalStdlibApi::class)

package br.edu.ifsp.aluno.gloriaporte.moviesmanager.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.R
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.databinding.GenderHeaderBinding
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.databinding.GenderItemBinding
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Gender


class GenderAdapter(context: Context, private val genders: Array<Gender>) :
    ArrayAdapter<Gender>(context, R.layout.gender_item, genders) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val gender = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.gender_item, parent, false)

        val ivGender: AppCompatImageView = view.findViewById(R.id.ivGender)
        val tvGender: AppCompatTextView = view.findViewById(R.id.tvGender)

        ivGender.setImageResource(gender?.icon ?: R.mipmap.ic_heart)
        tvGender.text = gender?.description ?: ""

        return view
    }
}
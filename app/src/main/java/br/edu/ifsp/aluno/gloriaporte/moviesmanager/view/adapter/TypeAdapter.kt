package br.edu.ifsp.aluno.gloriaporte.moviesmanager.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.R
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Gender
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Type
import android.view.ViewGroup as ViewGroup1

class TypeAdapter(context: Context, private val types: Array<Type>) :
    ArrayAdapter<Type>(context, R.layout.type_item, types) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup1): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup1): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup1): View {
        val type = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.type_item, parent, false)

        val ivType: AppCompatImageView = view.findViewById(R.id.ivType)
        val tvType: AppCompatTextView = view.findViewById(R.id.tvType)

        ivType.setImageResource(type?.icon ?: R.mipmap.ic_heart)
        tvType.text = type?.description ?: ""

        return view
    }
}

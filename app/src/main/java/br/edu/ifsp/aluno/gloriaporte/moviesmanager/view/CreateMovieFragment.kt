package br.edu.ifsp.aluno.gloriaporte.moviesmanager.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.controller.MovieViewModel
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.controller.MovieViewModelFactory
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.databinding.FragmentCreateMovieBinding
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Gender
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Movie
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.model.entity.Type
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.view.adapter.GenderAdapter
import br.edu.ifsp.aluno.gloriaporte.moviesmanager.view.adapter.TypeAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

class CreateMovieFragment : Fragment() {
    private lateinit var fragmentCreateMovieBinding: FragmentCreateMovieBinding
    private lateinit var viewModel: MovieViewModel
    private var selectedImageUri: Uri? = null
    private lateinit var selectedGender: Gender
    private lateinit var selectedType: Type

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = MovieViewModelFactory(requireActivity().application)
        fragmentCreateMovieBinding = FragmentCreateMovieBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

        return fragmentCreateMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val commonLayout = fragmentCreateMovieBinding.commonLayout
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        commonLayout.imageBT.setOnClickListener {
            openGallery()
        }

        commonLayout.genderSP.adapter = GenderAdapter(requireContext())
        commonLayout.typeSP.adapter = TypeAdapter(requireContext())

        commonLayout.genderSP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedGender = Gender.values()[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nada a ser feito aqui
            }
        }

        commonLayout.typeSP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedType = Type.values()[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nada a ser feito aqui
            }
        }

        fragmentCreateMovieBinding.commonLayout.saveBt.setOnClickListener {
            if(validate()) {
                val name = commonLayout.nameET.text.toString()
                val releasedYear = commonLayout.releasedYearET.text.toString()
                val production = commonLayout.productionET.text.toString()
                val minutes = commonLayout.minutesET.text.toString().toLong()
                val watched = commonLayout.watchedCB.isChecked
                val rating = commonLayout.starsRB.rating
                val stars = rating.toInt()
                val img = selectedImageUri?.let { getImageBytes(it) }

                val movie = Movie(0, name, production, minutes, watched, stars, selectedGender, selectedType, releasedYear, img)

                viewModel.insertMovie(movie)
                Snackbar.make(
                    fragmentCreateMovieBinding.root,
                    "Filme adicionado com sucesso!",
                    Snackbar.LENGTH_SHORT
                ).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(commonLayout.nameET.context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getImageBytes(selectedImageUri: Uri?): ByteArray? {
        try {
            val inputStream: InputStream? = requireContext().contentResolver.openInputStream(selectedImageUri!!)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val stream = ByteArrayOutputStream()

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            return stream.toByteArray()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun openGallery() {
        Log.d("MyApp", "openGallery() called")
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, Companion.REQUEST_IMAGE_GALLERY)
    }

    private fun validate(): Boolean {
        if (
            fragmentCreateMovieBinding.commonLayout.nameET.text.isEmpty() ||
            fragmentCreateMovieBinding.commonLayout.releasedYearET.text.isEmpty() ||
            fragmentCreateMovieBinding.commonLayout.minutesET.text.isEmpty() ||
            fragmentCreateMovieBinding.commonLayout.productionET.text.isEmpty()
        ) {
            return false
        }
        return true
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("MyApp", "onActivityResult() called")
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            val imageBytes = selectedImageUri?.let { getImageBytes(it) }
            imageBytes?.let {
                fragmentCreateMovieBinding.commonLayout.imgIV.setImageBitmap(BitmapFactory.decodeByteArray(it, 0, it.size))
            }
        }
    }

    private fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }

    companion object {
        private const val REQUEST_IMAGE_GALLERY = 1
    }
}
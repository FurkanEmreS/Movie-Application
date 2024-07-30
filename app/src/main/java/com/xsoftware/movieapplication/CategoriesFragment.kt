package com.xsoftware.movieapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.xsoftware.movieapplication.databinding.FragmentCategoriesBinding


class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TextView'lara tıklama olaylarını ekle
        binding.textHorror.setOnClickListener {
            (activity as? MainActivity)?.showHorrorMovies()
        }

        binding.textComedy.setOnClickListener {
            (activity as? MainActivity)?.showComedyMovies()
        }

        binding.textTurkish.setOnClickListener {
            Toast.makeText(context, "Turkish Selected", Toast.LENGTH_SHORT).show()
        }

        binding.textAction.setOnClickListener {
            (activity as? MainActivity)?.showActionMovies()


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
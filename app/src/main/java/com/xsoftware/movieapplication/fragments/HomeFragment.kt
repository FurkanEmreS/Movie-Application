package com.xsoftware.movieapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.xsoftware.movieapplication.MainActivity
import com.xsoftware.movieapplication.R
import com.xsoftware.movieapplication.databinding.FragmentHomeBinding




class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        val categoriesButton = view.findViewById<Button>(R.id.catagoriesButton)

        // İlk açılışta Movies butonunu seçili yap
        if (savedInstanceState == null) {
            binding.segmentedGroup.check(R.id.btnMovies) // Movies butonunu seçili yap
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MainFragment()) // İlk açılışta MoviesFragment göster
                .commit()
        }

        categoriesButton.setOnClickListener {
            // Seçili olan butonu kontrol et
            when (binding.segmentedGroup.checkedButtonId) {
                R.id.btnMovies -> {
                    (activity as? MainActivity)?.showCategoryMovie()
                }

                R.id.btnSeries -> {
                    (activity as? MainActivity)?.showCategorySeries()
                }
            }
        }

        // Segmented button click listener
        binding.segmentedGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnMovies -> {
                        parentFragmentManager.beginTransaction()
                            .setCustomAnimations(
                                R.anim.slide_in_left,
                                R.anim.slide_out_right,
                                R.anim.slide_in_right,
                                R.anim.slide_out_left
                            )
                            .replace(R.id.fragmentContainer, MainFragment())
                            .commit()
                    }
                    R.id.btnSeries -> {
                        parentFragmentManager.beginTransaction()
                            .setCustomAnimations(
                                R.anim.slide_in_right,
                                R.anim.slide_out_left,
                                R.anim.slide_in_left,
                                R.anim.slide_out_right
                            )
                            .replace(R.id.fragmentContainer, SeriesFragment())
                            .commit()
                    }
                }
            }
        }
    }
}
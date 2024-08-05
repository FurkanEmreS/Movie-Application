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
        val categoriesButton : Button = view.findViewById(R.id.catagoriesButton)

        categoriesButton.setOnClickListener {
            (activity as? MainActivity)?.showCategoryMovie()
        }



        // İlk açılışta MoviesFragment göster
        if (savedInstanceState == null) {
            parentFragmentManager.beginTransaction()

                .replace(R.id.fragmentContainer, MainFragment())
                .commit()
        }

        // Movies button click listener
        binding.btnMovies.setOnClickListener {
            parentFragmentManager.beginTransaction(). setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )




                .replace(R.id.fragmentContainer, MainFragment())
                .addToBackStack(null)
                .commit()
        }

        // Series button click listener
        binding.btnSeries.setOnClickListener {
            parentFragmentManager.beginTransaction(). setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )

                .replace(R.id.fragmentContainer, SeriesFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
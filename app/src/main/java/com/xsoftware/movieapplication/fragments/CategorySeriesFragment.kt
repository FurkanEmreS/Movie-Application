package com.xsoftware.movieapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.xsoftware.movieapplication.MainActivity
import com.xsoftware.movieapplication.adapters.SeriesCategoryAdapter
import com.xsoftware.movieapplication.databinding.FragmentCategorySeriesBinding
import com.xsoftware.movieapplication.models.Genre
import com.xsoftware.movieapplication.models.GenreResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.appcompat.widget.Toolbar
import com.xsoftware.movieapplication.R

class CategorySeriesFragment : Fragment(), SeriesCategoryAdapter.OnItemClickListener {

    private lateinit var binding: FragmentCategorySeriesBinding
    private lateinit var toolbar: Toolbar
    private lateinit var titleText: TextView
    private lateinit var backButton: ImageView
    private lateinit var categoriesButton: Button
    private lateinit var movieLogo: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategorySeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        titleText = view.findViewById(R.id.toolbar_title)
        titleText.text = "Categories"
        backButton = view.findViewById(R.id.back_button)
        backButton.setOnClickListener{
            (activity as? MainActivity)?.showHomeFragment()
        }

        categoriesButton = view.findViewById(R.id.catagoriesButton)
        categoriesButton.visibility = View.GONE
        movieLogo = view.findViewById(R.id.iv_logo)
        movieLogo.visibility = View.GONE



        // RecyclerView ayarları
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.categoryRecyclerView.setHasFixedSize(true)

        // Kategorileri API'den çek
        fetchGenres()
    }

    private fun fetchGenres() {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTvGenres().enqueue(object : Callback<GenreResponse> {
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                val genres = response.body()?.genres ?: emptyList()
                binding.categoryRecyclerView.adapter = SeriesCategoryAdapter(genres, this@CategorySeriesFragment)
            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                // Hata durumunda yapılacaklar
            }
        })
    }

    override fun onItemClick(genre: Genre) {
        (activity as? MainActivity)?.showSeriesCategory(genre)
    }
}
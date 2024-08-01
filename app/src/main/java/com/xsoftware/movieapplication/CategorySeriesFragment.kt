package com.xsoftware.movieapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.xsoftware.movieapplication.databinding.FragmentCategorySeriesBinding
import com.xsoftware.movieapplication.models.Genre
import com.xsoftware.movieapplication.models.GenreResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategorySeriesFragment : Fragment(), SeriesCategoryAdapter.OnItemClickListener {

    private lateinit var binding: FragmentCategorySeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategorySeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
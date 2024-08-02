package com.xsoftware.movieapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xsoftware.movieapplication.MainActivity
import com.xsoftware.movieapplication.R
import com.xsoftware.movieapplication.adapters.SeriesAdapter
import com.xsoftware.movieapplication.databinding.FragmentSeriesCategoryBinding
import com.xsoftware.movieapplication.models.Series
import com.xsoftware.movieapplication.models.SeriesResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriesCategoryFragment : Fragment(), SeriesAdapter.OnItemClickListener {

    private lateinit var binding: FragmentSeriesCategoryBinding
    private var genreId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            genreId = it.getInt("genreId", 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbarImage : ImageView = view.findViewById(R.id.iv_logo)
        toolbarImage.visibility = View.GONE
        val backButton : ImageView = view.findViewById(R.id.back_button)
        backButton.setOnClickListener{
            (activity as? MainActivity)?.showSeriesList()

        }

        binding.viewAllSeriesButton.setOnClickListener{
            (activity as? MainActivity)?.showAllSeriesCategory(genreId)
        }

        // RecyclerView ayarları
        binding.rvPopularSeries.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvPopularSeries.setHasFixedSize(true)

        binding.rvTopRatedSeries.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTopRatedSeries.setHasFixedSize(true)

        binding.rvOnTheAirSeries.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvOnTheAirSeries.setHasFixedSize(true)

        binding.rvAiringTodaySeries.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvAiringTodaySeries.setHasFixedSize(true)

        getSeriesData()
    }

    private fun getSeriesData() {
        getPopularSeries { series -> binding.rvPopularSeries.adapter = SeriesAdapter(series, this) }
        getTopRatedSeries { series -> binding.rvTopRatedSeries.adapter = SeriesAdapter(series, this) }
        getUpComingSeries { series -> binding.rvOnTheAirSeries.adapter = SeriesAdapter(series, this) }
        getTurkishSeries { series -> binding.rvAiringTodaySeries.adapter = SeriesAdapter(series, this) }
    }

    private fun getPopularSeries(callback: (List<Series>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getPopularSeriesGenre(genreId).enqueue(object : Callback<SeriesResponse> {
            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                // Hata durumunu yönet
                Log.e("SeriesCategoryFragment", "Popüler dizi yüklenemedi: ${t.message}")
            }

            override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                response.body()?.series?.let { seriesList ->
                    callback(seriesList)
                } ?: run {
                    Log.e("SeriesCategoryFragment", "Boş yanıt alındı")
                }
            }
        })
    }

    private fun getTopRatedSeries(callback: (List<Series>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTopRatedSeriesGenre(genreId).enqueue(object : Callback<SeriesResponse> {
            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                // Hata durumunu yönet
                Log.e("SeriesCategoryFragment", "En çok oy alan diziler yüklenemedi: ${t.message}")
            }

            override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                response.body()?.series?.let { seriesList ->
                    callback(seriesList)
                } ?: run {
                    Log.e("SeriesCategoryFragment", "Boş yanıt alındı")
                }
            }
        })
    }

    private fun getUpComingSeries(callback: (List<Series>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getUpComingSeriesGenre(genreId).enqueue(object : Callback<SeriesResponse> {
            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                // Hata durumunu yönet
                Log.e("SeriesCategoryFragment", "Yayınlanmakta olan diziler yüklenemedi: ${t.message}")
            }

            override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                response.body()?.series?.let { seriesList ->
                    callback(seriesList)
                } ?: run {
                    Log.e("SeriesCategoryFragment", "Boş yanıt alındı")
                }
            }
        })
    }

    private fun getTurkishSeries(callback: (List<Series>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTurkishSeriesGenre(genreId).enqueue(object : Callback<SeriesResponse> {
            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                // Hata durumunu yönet
                Log.e("SeriesCategoryFragment", "Bugün yayınlanan diziler yüklenemedi: ${t.message}")
            }

            override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                response.body()?.series?.let { seriesList ->
                    callback(seriesList)
                } ?: run {
                    Log.e("SeriesCategoryFragment", "Boş yanıt alındı")
                }
            }
        })
    }

    override fun onItemClick(series: Series) {
        Log.d("SERIES", series.name ?: "-")
        (activity as? MainActivity)?.addSeriesDetail(series)
    }
}
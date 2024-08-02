package com.xsoftware.movieapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.xsoftware.movieapplication.MainActivity
import com.xsoftware.movieapplication.adapters.SeriesAdapter
import com.xsoftware.movieapplication.adapters.SeriesCastAdapter
import com.xsoftware.movieapplication.databinding.FragmentSeriesDetailBinding
import com.xsoftware.movieapplication.models.Series
import com.xsoftware.movieapplication.models.SeriesCast
import com.xsoftware.movieapplication.models.SeriesCastResponse
import com.xsoftware.movieapplication.models.SeriesResponse
import com.xsoftware.movieapplication.services.APIConstants
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriesDetailFragment : Fragment(), SeriesAdapter.OnItemClickListener {

    private lateinit var binding: FragmentSeriesDetailBinding
    private var series: Series? = null

    override fun onItemClick(series: Series) {
        Log.d("SERIES", series.name ?: "-")
        (activity as? MainActivity)?.addSeriesDetail(series)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        series = requireArguments().get("series") as? Series

        binding.castRecycleView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleViewSimilarSeries.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        series?.let {
            binding.textView4.text = it.name
            binding.textView5.text = it.overview
            binding.relaseDateText.text = "Release Date: " + it.release
            binding.voteAvarageText.text = "Vote Average: " + it.average.toString()

            Glide.with(view).load(APIConstants.imageBaseUrl + it.poster).into(binding.imageView)
            fetchSimilarSeries(it.id!!) { series ->
                binding.recycleViewSimilarSeries.adapter = SeriesAdapter(series, this)
            }
            getCastData(it.id!!) { casts -> binding.castRecycleView.adapter = SeriesCastAdapter(casts) }
        }
    }

    private fun fetchSimilarSeries(seriesId: String, callback: (List<Series>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getSeriesById(seriesId).enqueue(object : Callback<SeriesResponse> {
            override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                callback(response.body()!!.series)
            }

            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                Log.e("SeriesDetailFragment", "Failed to fetch similar series", t)
            }
        })
    }

    private fun getCastData(seriesId: String, callback: (List<SeriesCast>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getSeriesCastById(seriesId).enqueue(object : Callback<SeriesCastResponse> {
            override fun onResponse(
                call: Call<SeriesCastResponse>,
                response: Response<SeriesCastResponse>
            ) {
                callback(response.body()!!.seriesCasts)
            }

            override fun onFailure(call: Call<SeriesCastResponse>, t: Throwable) {
                Log.e("SeriesDetailFragment", "Failed to fetch series cast data", t)
            }
        })
    }
}
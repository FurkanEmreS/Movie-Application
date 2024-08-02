package com.xsoftware.movieapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xsoftware.movieapplication.MainActivity
import com.xsoftware.movieapplication.R
import com.xsoftware.movieapplication.adapters.SeriesAdapter
import com.xsoftware.movieapplication.databinding.FragmentSeriesBinding
import com.xsoftware.movieapplication.models.Series
import com.xsoftware.movieapplication.models.SeriesResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class SeriesFragment : Fragment(), SeriesAdapter.OnItemClickListener {

    private lateinit var binding: FragmentSeriesBinding

    override fun onItemClick(series: Series) {
        Log.d("SERIES", series.name ?: "-")
        (activity as? MainActivity)?.addSeriesDetail(series)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton : ImageView = view.findViewById(R.id.back_button)
        backButton.visibility = View.GONE


        binding.rvPopularSeries.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvPopularSeries.setHasFixedSize(true)

        binding.rvTopRatedSeries.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTopRatedSeries.setHasFixedSize(true)

        binding.rvOnTheAirSeries.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvOnTheAirSeries.setHasFixedSize(true)

        binding.rvAiringTodaySeries.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvAiringTodaySeries.setHasFixedSize(true)

        getSeriesData()

        binding.catagoriesButton.setOnClickListener {
            (activity as? MainActivity)?.showCategorySeries()
        }
    }

    private fun getSeriesData() {
        getPopularSeries { series -> binding.rvPopularSeries.adapter = SeriesAdapter(series, this) }
        getTopRatedSeries { series -> binding.rvTopRatedSeries.adapter = SeriesAdapter(series, this) }
        getOnTheAirSeries { series -> binding.rvOnTheAirSeries.adapter = SeriesAdapter(series, this) }
        getAiringTodaySeries { series -> binding.rvAiringTodaySeries.adapter = SeriesAdapter(series, this) }
    }

    private fun getPopularSeries(callback: (List<Series>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getPopularSeries().enqueue(object : Callback<SeriesResponse> {
            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {}
            override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                callback(response.body()!!.series)
            }
        })
    }

    private fun getTopRatedSeries(callback: (List<Series>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTopRatedSeries().enqueue(object : Callback<SeriesResponse> {
            override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                callback(response.body()!!.series)
            }

            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {}
        })
    }

    private fun getOnTheAirSeries(callback: (List<Series>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getOnTheAirSeries().enqueue(object : Callback<SeriesResponse> {
            override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                callback(response.body()!!.series)
            }

            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {}
        })
    }

    private fun getAiringTodaySeries(callback: (List<Series>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getAiringTodaySeries().enqueue(object : Callback<SeriesResponse> {
            override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                callback(response.body()!!.series)
            }

            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {}
        })
    }
}
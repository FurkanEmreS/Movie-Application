package com.xsoftware.movieapplication.fragments

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xsoftware.movieapplication.MainActivity
import com.xsoftware.movieapplication.R
import com.xsoftware.movieapplication.adapters.SeriesAdapter
import com.xsoftware.movieapplication.databinding.FragmentAllSeriesCategoryBinding
import com.xsoftware.movieapplication.models.Series
import com.xsoftware.movieapplication.models.SeriesResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllSeriesCategoryFragment : Fragment(), SeriesAdapter.OnItemClickListener {

    private lateinit var binding: FragmentAllSeriesCategoryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: SeriesAdapter
    private var isLoading = false
    private var currentPage = 1
    private val totalPage = 6
    private val seriesList = mutableListOf<Series>()
    private var layoutManagerState: Parcelable? = null
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
        binding = FragmentAllSeriesCategoryBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        progressBar = binding.progressBar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SeriesAdapter(seriesList, this)
        val layoutManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter


        val toolbarImage : ImageView = view.findViewById(R.id.iv_logo)
        toolbarImage.visibility = View.GONE
        val backButton : ImageView = view.findViewById(R.id.back_button)
        backButton.setOnClickListener{
            (activity as? MainActivity)?.popBackStack()

        }

        // Durum geri yükleme
        if (savedInstanceState != null) {
            currentPage = savedInstanceState.getInt("currentPage", 1)
            layoutManagerState = savedInstanceState.getParcelable("layoutManagerState")
            val savedSeriesList: List<Series>? = savedInstanceState.getParcelableArrayList("seriesList")
            if (savedSeriesList != null) {
                seriesList.addAll(savedSeriesList)
                adapter.notifyDataSetChanged()
            }
        } else {
            loadSeries()
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && firstVisibleItemPosition + visibleItemCount >= totalItemCount && firstVisibleItemPosition >= 0) {
                    if (currentPage < totalPage) {
                        currentPage++
                        loadMoreSeries()
                    }
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentPage", currentPage)
        outState.putParcelableArrayList("seriesList", ArrayList(seriesList))
        outState.putParcelable("layoutManagerState", recyclerView.layoutManager?.onSaveInstanceState())
    }

    override fun onResume() {
        super.onResume()
        layoutManagerState?.let {
            recyclerView.layoutManager?.onRestoreInstanceState(it)
        }
    }

    private fun loadSeries() {
        if (seriesList.isEmpty()) {
            isLoading = true
            progressBar.visibility = View.VISIBLE

            val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
            apiService.getSeriesByGenreAndPage(genreId, currentPage).enqueue(object : Callback<SeriesResponse> {
                override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                    progressBar.visibility = View.GONE
                    val series = response.body()?.series?.filter { it.poster != null && it.poster.isNotEmpty() } ?: emptyList()
                    seriesList.addAll(series)
                    adapter.notifyDataSetChanged()
                    isLoading = false
                }

                override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    isLoading = false
                }
            })
        }
    }

    private fun loadMoreSeries() {
        isLoading = true
        progressBar.visibility = View.VISIBLE

        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getSeriesByGenreAndPage(genreId, currentPage).enqueue(object : Callback<SeriesResponse> {
            override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                progressBar.visibility = View.GONE
                val series = response.body()?.series?.filter { it.poster != null && it.poster.isNotEmpty() } ?: emptyList()
                seriesList.addAll(series)
                adapter.notifyDataSetChanged()
                isLoading = false
            }

            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                isLoading = false
            }
        })
    }

    override fun onItemClick(series: Series) {
        (activity as? MainActivity)?.addSeriesDetail(series)
    }
}
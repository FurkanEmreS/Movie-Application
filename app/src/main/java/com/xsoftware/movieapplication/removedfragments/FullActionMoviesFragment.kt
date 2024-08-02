package com.xsoftware.movieapplication.removedfragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xsoftware.movieapplication.MainActivity
import com.xsoftware.movieapplication.R
import com.xsoftware.movieapplication.adapters.MovieAdapter
import com.xsoftware.movieapplication.models.Movie
import com.xsoftware.movieapplication.models.MovieResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FullActionMoviesFragment : Fragment(), MovieAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: MovieAdapter
    private var isLoading = false
    private var currentPage = 1
    private val totalPage = 5
    private val moviesList = mutableListOf<Movie>()
    private var layoutManagerState: Parcelable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_full_action_movies, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter(moviesList, this)
        val layoutManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        // Durum geri yükleme
        if (savedInstanceState != null) {
            currentPage = savedInstanceState.getInt("currentPage", 1)
            layoutManagerState = savedInstanceState.getParcelable("layoutManagerState")
            val savedMoviesList: List<Movie>? = savedInstanceState.getParcelableArrayList("moviesList")
            if (savedMoviesList != null) {
                moviesList.addAll(savedMoviesList)
                adapter.notifyDataSetChanged()
            }
        } else {
            loadMovies()
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
                        loadMoreMovies()
                    }
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentPage", currentPage)
        outState.putParcelableArrayList("moviesList", ArrayList(moviesList))
        outState.putParcelable("layoutManagerState", recyclerView.layoutManager?.onSaveInstanceState())
    }

    override fun onResume() {
        super.onResume()
        layoutManagerState?.let {
            recyclerView.layoutManager?.onRestoreInstanceState(it)
        }
    }

    private fun loadMovies() {
        if (moviesList.isEmpty()) {
            isLoading = true
            progressBar.visibility = View.VISIBLE

            val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
            apiService.getActionMovies(page = currentPage).enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    progressBar.visibility = View.GONE
                    val movies = response.body()?.movies ?: emptyList()
                    moviesList.addAll(movies)
                    adapter.notifyDataSetChanged()
                    isLoading = false
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    isLoading = false
                }
            })
        }
    }

    private fun loadMoreMovies() {
        isLoading = true
        progressBar.visibility = View.VISIBLE

        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getActionMovies(page = currentPage).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                progressBar.visibility = View.GONE
                val movies = response.body()?.movies ?: emptyList()
                moviesList.addAll(movies)
                adapter.notifyDataSetChanged()
                isLoading = false
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                isLoading = false
            }
        })
    }

    override fun onItemClick(movie: Movie) {
        (activity as? MainActivity)?.apply {
            addMovieDetail(movie)
        }
    }
}
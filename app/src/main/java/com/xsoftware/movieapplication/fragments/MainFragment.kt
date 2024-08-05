package com.xsoftware.movieapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.xsoftware.movieapplication.MainActivity
import com.xsoftware.movieapplication.R
import com.xsoftware.movieapplication.adapters.MovieAdapter
import com.xsoftware.movieapplication.databinding.FragmentMainBinding
import com.xsoftware.movieapplication.models.Movie
import com.xsoftware.movieapplication.models.MovieResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment(), MovieAdapter.OnItemClickListener {

    private lateinit var binding: FragmentMainBinding

    private var isLoading = false
    private var currentHorrorPage = 1
    private var totalHorrorPage = 5
    private var currentTurkishPage=1
    private var totalTurkishPage=5
    private var currentActionPage = 1
    private var totalActionPage = 5
    private var currentComedyPage = 1
    private var totalComedyPage = 5


    override fun onItemClick(movie: Movie) {
        Log.d("MOVIE", movie.title ?: "-")
        (activity as? MainActivity)?.addMovieDetail(movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setupRecyclerViews()
        getBothMovieData()
        getGenreMovieData()
    }

    private fun setupRecyclerViews() {
        binding.rvPopularMovies.layoutManager =
            LinearLayoutManager(view?.context, RecyclerView.HORIZONTAL, false)
        binding.rvPopularMovies.setHasFixedSize(true)

        binding.rvUpcomingMovies.layoutManager =
            LinearLayoutManager(view?.context, RecyclerView.HORIZONTAL, false)
        binding.rvUpcomingMovies.setHasFixedSize(true)

        binding.rvTopRatedMovies.layoutManager =
            LinearLayoutManager(view?.context, RecyclerView.HORIZONTAL, false)
        binding.rvTopRatedMovies.setHasFixedSize(true)

        binding.rvNowPlaying.layoutManager =
            LinearLayoutManager(view?.context, RecyclerView.HORIZONTAL, false)
        binding.rvNowPlaying.setHasFixedSize(true)

        binding.rvActionMovies.layoutManager =
            LinearLayoutManager(view?.context, RecyclerView.HORIZONTAL, false)
        binding.rvComedyMovies.layoutManager =
            LinearLayoutManager(view?.context, RecyclerView.HORIZONTAL, false)
        binding.rvHorrorMovies.layoutManager =
            LinearLayoutManager(view?.context, RecyclerView.HORIZONTAL, false)
        binding.rvTurkishMovies.layoutManager =
            LinearLayoutManager(view?.context, RecyclerView.HORIZONTAL, false)

        binding.rvHorrorMovies.addOnScrollListener(createOnScrollListener {
            if (currentHorrorPage < totalHorrorPage) {
                currentHorrorPage++
                loadMoreHorrorMovies()
            }
        })

        binding.rvTurkishMovies.addOnScrollListener(createOnScrollListener {
            if (currentTurkishPage < totalTurkishPage) {
                currentTurkishPage++
                loadMoreTurkishMovies()
            }
        })

        binding.rvComedyMovies.addOnScrollListener(createOnScrollListener {
            if (currentComedyPage < totalComedyPage) {
                currentComedyPage++
                loadMoreComedyMovies()
            }
        })

        binding.rvActionMovies.addOnScrollListener(createOnScrollListener {
            if (currentActionPage < totalActionPage) {
                currentActionPage++
                loadMoreActionMovies()
            }
        })
    }

    private fun createOnScrollListener(loadMore: () -> Unit): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && firstVisibleItemPosition + visibleItemCount >= totalItemCount && firstVisibleItemPosition >= 0) {
                    loadMore()
                }
            }
        }
    }

    private fun getBothMovieData() {
        getPopularMovieDatas { movies ->
            binding.rvPopularMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getUpcomingMoviesDatas { movies ->
            binding.rvUpcomingMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getTopRatedMovies { movies ->
            binding.rvTopRatedMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getNowPlayingMoviesData { movies ->
            binding.rvNowPlaying.adapter = MovieAdapter(movies.toMutableList(), this)
        }
    }

    private fun getGenreMovieData() {
        getActionMovies(currentActionPage) { movies ->
            binding.rvActionMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getComedyMovies(currentComedyPage) { movies ->
            binding.rvComedyMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getHorrorMovies(currentHorrorPage) { movies ->
            binding.rvHorrorMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }

        getTurkishMovies(currentTurkishPage) { movies ->
            binding.rvTurkishMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
    }

    private fun loadMoreHorrorMovies() {
        isLoading = true
        getHorrorMovies(currentHorrorPage) { movies ->
            (binding.rvHorrorMovies.adapter as MovieAdapter).addMovies(movies)
            isLoading = false
        }
    }

    private fun loadMoreTurkishMovies(){
        isLoading = true
        getTurkishMovies(currentTurkishPage) { movies ->
            (binding.rvTurkishMovies.adapter as MovieAdapter).addMovies(movies)
            isLoading = false
        }
    }

    private fun loadMoreComedyMovies() {
        isLoading = true
        getComedyMovies(currentComedyPage) { movies ->
            (binding.rvComedyMovies.adapter as MovieAdapter).addMovies(movies)
            isLoading = false
        }
    }

    private fun loadMoreActionMovies() {
        isLoading = true
        getActionMovies(currentActionPage) { movies ->
            (binding.rvActionMovies.adapter as MovieAdapter).addMovies(movies)
            isLoading = false
        }
    }

    private fun getPopularMovieDatas(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }
        })
    }

    private fun getUpcomingMoviesDatas(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getUpcomingMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getTopRatedMovies(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTopRatedMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getNowPlayingMoviesData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getNowPlayingMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getActionMovies(page: Int, callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getActionMovies(page = page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                isLoading = false
            }
        })
    }

    private fun getComedyMovies(page: Int, callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getComedyMovies(page = page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                isLoading = false
            }
        })
    }

    private fun getHorrorMovies(page: Int, callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getHorrorMovies(page = page).enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                isLoading = false
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }
        })
    }

    private fun getTurkishMovies(page: Int, callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTurkishMovies(page = page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                isLoading = false
            }
        })
    }


}
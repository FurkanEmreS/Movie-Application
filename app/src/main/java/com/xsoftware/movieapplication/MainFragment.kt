package com.xsoftware.movieapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xsoftware.movieapplication.databinding.FragmentMainBinding
import com.xsoftware.movieapplication.models.Movie
import com.xsoftware.movieapplication.models.MovieResponse
import com.xsoftware.movieapplication.models.Series
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment(), MovieAdapter.OnItemClickListener {



    private lateinit var binding: FragmentMainBinding

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
        val seriesButton :Button = view.findViewById(R.id.seriesButton)

        seriesButton.setOnClickListener{
            (activity as? MainActivity)?.showSeriesList()

        }


        binding.rvPopularMovies.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvPopularMovies.setHasFixedSize(true)

        binding.rvUpcomingMovies.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvUpcomingMovies.setHasFixedSize(true)

        binding.rvTopRatedMovies.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTopRatedMovies.setHasFixedSize(true)

        binding.rvNowPlaying.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvNowPlaying.setHasFixedSize(true)

        getBothMovieData()
    }

    private fun getBothMovieData() {
        getPopularMovieDatas { movies -> binding.rvPopularMovies.adapter = MovieAdapter(movies, this) }
        getUpcomingMoviesDatas { movies -> binding.rvUpcomingMovies.adapter = MovieAdapter(movies, this) }
        getTopRatedMovies { movies -> binding.rvTopRatedMovies.adapter = MovieAdapter(movies, this) }
        getNowPlayingMoviesData { movies -> binding.rvNowPlaying.adapter = MovieAdapter(movies, this) }
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
}

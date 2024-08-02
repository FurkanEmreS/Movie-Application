package com.xsoftware.movieapplication.removedfragments

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
import com.xsoftware.movieapplication.adapters.MovieAdapter
import com.xsoftware.movieapplication.databinding.FragmentRomanceMoviesBinding
import com.xsoftware.movieapplication.models.Movie
import com.xsoftware.movieapplication.models.MovieResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RomanceMoviesFragment : Fragment(), MovieAdapter.OnItemClickListener {
    private lateinit var binding: FragmentRomanceMoviesBinding


    override fun onItemClick(movie: Movie) {
        Log.d("MOVIE", movie.title ?: "-")
        (activity as? MainActivity)?.addMovieDetail(movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRomanceMoviesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val toolbarImage : ImageView = view.findViewById(R.id.iv_logo)
        toolbarImage.visibility = View.GONE
        val backButton : ImageView = view.findViewById(R.id.back_button)
        backButton.setOnClickListener{
            (activity as? MainActivity)?.showMainList()

        }


        binding.rvPopularRomanceMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvPopularRomanceMovies.setHasFixedSize(true)

        binding.rvUpcomingRomanceMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvUpcomingRomanceMovies.setHasFixedSize(true)

        binding.rvTopRatedRomanceMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTopRatedRomanceMovies.setHasFixedSize(true)

        binding.rvTurkishRomanceMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTurkishRomanceMovies.setHasFixedSize(true)







        getBothMovieData()

    }

    private fun getBothMovieData() {
        getPopularRomanceMovies { movies ->
            binding.rvPopularRomanceMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getUpComingRomanceMovies { movies ->
            binding.rvUpcomingRomanceMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getTopRatedRomanceMovies { movies ->
            binding.rvTopRatedRomanceMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getTurkishRomanceMovies { movies ->
            binding.rvTurkishRomanceMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
    }




    private fun getPopularRomanceMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getPopularRomanceMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }
        })
    }

    private fun getTopRatedRomanceMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTopRatedRomanceMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getUpComingRomanceMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getUpComingRomanceMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getTurkishRomanceMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTurkishRomanceMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }



}
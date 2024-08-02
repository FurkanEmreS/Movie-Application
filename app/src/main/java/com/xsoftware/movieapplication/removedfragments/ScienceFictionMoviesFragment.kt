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
import com.xsoftware.movieapplication.databinding.FragmentScienceFictionMoviesBinding
import com.xsoftware.movieapplication.models.Movie
import com.xsoftware.movieapplication.models.MovieResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ScienceFictionMoviesFragment : Fragment(), MovieAdapter.OnItemClickListener {
    private lateinit var binding: FragmentScienceFictionMoviesBinding


    override fun onItemClick(movie: Movie) {
        Log.d("MOVIE", movie.title ?: "-")
        (activity as? MainActivity)?.addMovieDetail(movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScienceFictionMoviesBinding.inflate(inflater, container, false)
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


        binding.rvPopularScienceFictionMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvPopularScienceFictionMovies.setHasFixedSize(true)

        binding.rvUpcomingScienceFictionMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvUpcomingScienceFictionMovies.setHasFixedSize(true)

        binding.rvTopRatedScienceFictionMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTopRatedScienceFictionMovies.setHasFixedSize(true)

        binding.rvTurkishScienceFictionMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTopRatedScienceFictionMovies.setHasFixedSize(true)







        getBothMovieData()

    }

    private fun getBothMovieData() {
        getPopularScienceFictionMovies { movies ->
            binding.rvPopularScienceFictionMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getUpComingScienceFictionMovies { movies ->
            binding.rvUpcomingScienceFictionMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getTopRatedScienceFictionMovies { movies ->
            binding.rvTopRatedScienceFictionMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getTurkishScienceFictionMovies { movies ->
            binding.rvTurkishScienceFictionMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
    }




    private fun getPopularScienceFictionMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getPopularScienceFictionMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }
        })
    }

    private fun getTopRatedScienceFictionMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTopRatedScienceFictionMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getUpComingScienceFictionMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getUpComingScienceFictionMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getTurkishScienceFictionMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTurkishScienceFictionMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }



}
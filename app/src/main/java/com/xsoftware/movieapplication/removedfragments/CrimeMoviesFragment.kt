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
import com.xsoftware.movieapplication.databinding.FragmentCrimeMoviesBinding
import com.xsoftware.movieapplication.models.Movie
import com.xsoftware.movieapplication.models.MovieResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CrimeMoviesFragment : Fragment(), MovieAdapter.OnItemClickListener {
    private lateinit var binding: FragmentCrimeMoviesBinding


    override fun onItemClick(movie: Movie) {
        Log.d("MOVIE", movie.title ?: "-")
        (activity as? MainActivity)?.addMovieDetail(movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCrimeMoviesBinding.inflate(inflater, container, false)
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


        binding.rvPopularCrimeMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvPopularCrimeMovies.setHasFixedSize(true)

        binding.rvUpcomingCrimeMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvUpcomingCrimeMovies.setHasFixedSize(true)

        binding.rvTopRatedCrimeMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTopRatedCrimeMovies.setHasFixedSize(true)

        binding.rvTurkishCrimeMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTurkishCrimeMovies.setHasFixedSize(true)







        getBothMovieData()

    }

    private fun getBothMovieData() {
        getPopularCrimeMovies { movies ->
            binding.rvPopularCrimeMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getUpComingCrimeMovies { movies ->
            binding.rvUpcomingCrimeMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getTopRatedCrimeMovies { movies ->
            binding.rvTopRatedCrimeMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getTurkishCrimeMovies { movies ->
            binding.rvTurkishCrimeMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
    }




    private fun getPopularCrimeMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getPopularCrimeMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }
        })
    }

    private fun getTopRatedCrimeMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTopRatedCrimeMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getUpComingCrimeMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getUpComingCrimeMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getTurkishCrimeMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTurkishCrimeMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }



}
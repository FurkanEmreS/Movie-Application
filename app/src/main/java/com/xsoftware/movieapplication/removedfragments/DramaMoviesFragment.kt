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
import com.xsoftware.movieapplication.databinding.FragmentDramaMoviesBinding
import com.xsoftware.movieapplication.models.Movie
import com.xsoftware.movieapplication.models.MovieResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DramaMoviesFragment : Fragment(), MovieAdapter.OnItemClickListener {
    private lateinit var binding: FragmentDramaMoviesBinding


    override fun onItemClick(movie: Movie) {
        Log.d("MOVIE", movie.title ?: "-")
        (activity as? MainActivity)?.addMovieDetail(movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDramaMoviesBinding.inflate(inflater, container, false)
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


        binding.rvPopularDramaMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvPopularDramaMovies.setHasFixedSize(true)

        binding.rvUpcomingDramaMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvUpcomingDramaMovies.setHasFixedSize(true)

        binding.rvTopRatedDramaMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTopRatedDramaMovies.setHasFixedSize(true)

        binding.rvTurkishDramaMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTurkishDramaMovies.setHasFixedSize(true)







        getBothMovieData()

    }

    private fun getBothMovieData() {
        getPopularDramaMovies { movies ->
            binding.rvPopularDramaMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getUpComingDramaMovies { movies ->
            binding.rvUpcomingDramaMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getTopRatedDramaMovies { movies ->
            binding.rvTopRatedDramaMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getTurkishDramaMovies { movies ->
            binding.rvTurkishDramaMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
    }




    private fun getPopularDramaMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getPopularDramaMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }
        })
    }

    private fun getTopRatedDramaMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTopRatedDramaMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getUpComingDramaMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getUpComingDramaMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getTurkishDramaMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTurkishDramaMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }



}
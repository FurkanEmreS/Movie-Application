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
import com.xsoftware.movieapplication.databinding.FragmentAnimationMoviesBinding
import com.xsoftware.movieapplication.models.Movie
import com.xsoftware.movieapplication.models.MovieResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimationMoviesFragment : Fragment() , MovieAdapter.OnItemClickListener{
    private lateinit var binding: FragmentAnimationMoviesBinding


    override fun onItemClick(movie: Movie) {
        Log.d("MOVIE", movie.title ?: "-")
        (activity as? MainActivity)?.addMovieDetail(movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationMoviesBinding.inflate(inflater, container, false)
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


        binding.rvPopularAnimationMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvPopularAnimationMovies.setHasFixedSize(true)

        binding.rvUpcomingAnimationMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvUpcomingAnimationMovies.setHasFixedSize(true)

        binding.rvTopRatedAnimationMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTopRatedAnimationMovies.setHasFixedSize(true)

        binding.rvTurkishAnimationMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTurkishAnimationMovies.setHasFixedSize(true)







        getBothMovieData()

    }

    private fun getBothMovieData() {
        getPopularAnimationMovies { movies ->
            binding.rvPopularAnimationMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getUpComingAnimationMovies { movies ->
            binding.rvUpcomingAnimationMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getTopRatedAnimationMovies { movies ->
            binding.rvTopRatedAnimationMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getTurkishAnimationMovies { movies ->
            binding.rvTurkishAnimationMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
    }




    private fun getPopularAnimationMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getPopularAnimationMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }
        })
    }

    private fun getTopRatedAnimationMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTopRatedAnimationMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getUpComingAnimationMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getUpComingAnimationMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getTurkishAnimationMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTurkishAnimationMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }



}
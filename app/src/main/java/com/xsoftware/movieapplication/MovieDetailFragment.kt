package com.xsoftware.movieapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.xsoftware.movieapplication.databinding.FragmentMovieDetailBinding
import com.xsoftware.movieapplication.models.Movie
import com.xsoftware.movieapplication.models.MovieCast
import com.xsoftware.movieapplication.models.MovieCastResponse
import com.xsoftware.movieapplication.models.MovieResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailFragment : Fragment(), MovieAdapter.OnItemClickListener {

    private lateinit var binding: FragmentMovieDetailBinding
    private var movie: Movie? = null

    override fun onItemClick(movie: Movie) {
        Log.d("MOVIE", movie.title ?: "-")
        (activity as? MainActivity)?.addMovieDetail(movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = requireArguments().get("movie") as? Movie

        binding.castRecycleView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleViewSimilarMovies.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        movie?.let {
            binding.textView4.text = it.title
            binding.textView5.text = it.overview
            binding.relaseDateText.text = "Release Date: " + it.release
            binding.voteAvarageText.text = "Vote Average: " + it.average.toString()

            Glide.with(view).load(APIConstants.imageBaseUrl + it.poster).into(binding.imageView)
            fetchSimilarMovies(it.id!!) { movies -> binding.recycleViewSimilarMovies.adapter = MovieAdapter(movies, this) }
            getCastData(it.id!!) { casts -> binding.castRecycleView.adapter = CastAdapter(casts) }
        }
    }

    private fun fetchSimilarMovies(movieId: String, callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getMovieById(movieId).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getCastData(castId: String, callback: (List<MovieCast>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getCastById(castId).enqueue(object : Callback<MovieCastResponse> {
            override fun onResponse(call: Call<MovieCastResponse>, response: Response<MovieCastResponse>) {
                callback(response.body()!!.movieCasts)
            }

            override fun onFailure(call: Call<MovieCastResponse>, t: Throwable) {}
        })
    }
}

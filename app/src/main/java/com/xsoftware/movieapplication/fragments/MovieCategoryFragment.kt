package com.xsoftware.movieapplication.fragments

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
import com.xsoftware.movieapplication.databinding.FragmentMovieCategoryBinding
import com.xsoftware.movieapplication.models.Movie
import com.xsoftware.movieapplication.models.MovieResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieCategoryFragment : Fragment(), MovieAdapter.OnItemClickListener {

    private lateinit var binding: FragmentMovieCategoryBinding
    private var genreId: Int = 0
    private var genreName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            genreId = it.getInt("genreId", 0)
            genreName = it.getString("genreName", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbarImage: ImageView = view.findViewById(R.id.iv_logo)
        toolbarImage.visibility = View.GONE
        val backButton: ImageView = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            (activity as? MainActivity)?.showMainList()
        }

        binding.viewAllMoviesButton.setOnClickListener {
            (activity as? MainActivity)?.showAllMovieCategory(genreId)
        }

        // RecyclerView ayarları
        binding.rvPopularMovies.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvPopularMovies.setHasFixedSize(true)

        binding.rvTopRatedMovies.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTopRatedMovies.setHasFixedSize(true)

        binding.rvUpcomingMovies.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvUpcomingMovies.setHasFixedSize(true)

        binding.rvTurkishMovies.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTurkishMovies.setHasFixedSize(true)

        binding.catagoriesButton.setOnClickListener {
            (activity as? MainActivity)?.showCategoryMovie()
        }

        binding.catagoriesButton.text = genreName

        getMoviesData()
    }

    private fun getMoviesData() {
        getPopularMovies { movies -> binding.rvPopularMovies.adapter = MovieAdapter(movies.toMutableList(), this) }
        getTopRatedMovies { movies -> binding.rvTopRatedMovies.adapter = MovieAdapter(movies.toMutableList(), this) }
        getUpcomingMovies { movies -> binding.rvUpcomingMovies.adapter = MovieAdapter(movies.toMutableList(), this) }
        getTurkishMovies { movies -> binding.rvTurkishMovies.adapter = MovieAdapter(movies.toMutableList(), this) }
    }

    private fun getPopularMovies(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getPopularMoviesGenre(genreId).enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Hata durumunu yönet
                Log.e("MovieCategoryFragment", "Popüler filmler yüklenemedi: ${t.message}")
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.movies?.let { movieList ->
                    callback(movieList)
                } ?: run {
                    Log.e("MovieCategoryFragment", "Boş yanıt alındı")
                }
            }
        })
    }

    private fun getTopRatedMovies(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTopRatedMoviesGenre(genreId).enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Hata durumunu yönet
                Log.e("MovieCategoryFragment", "En çok oy alan filmler yüklenemedi: ${t.message}")
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.movies?.let { movieList ->
                    callback(movieList)
                } ?: run {
                    Log.e("MovieCategoryFragment", "Boş yanıt alındı")
                }
            }
        })
    }

    private fun getUpcomingMovies(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getUpcomingMoviesGenre(genreId).enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Hata durumunu yönet
                Log.e("MovieCategoryFragment", "Yaklaşan filmler yüklenemedi: ${t.message}")
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.movies?.let { movieList ->
                    callback(movieList)
                } ?: run {
                    Log.e("MovieCategoryFragment", "Boş yanıt alındı")
                }
            }
        })
    }

    private fun getTurkishMovies(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTurkishMoviesGenre(genreId).enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Hata durumunu yönet
                Log.e("MovieCategoryFragment", "Türk filmleri yüklenemedi: ${t.message}")
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.movies?.let { movieList ->
                    callback(movieList)
                } ?: run {
                    Log.e("MovieCategoryFragment", "Boş yanıt alındı")
                }
            }
        })
    }

    override fun onItemClick(movie: Movie) {
        Log.d("MOVIE", movie.title ?: "-")
        (activity as? MainActivity)?.addMovieDetail(movie)
    }
}
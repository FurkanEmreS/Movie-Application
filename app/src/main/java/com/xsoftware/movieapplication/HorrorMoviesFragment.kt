package com.xsoftware.movieapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xsoftware.movieapplication.databinding.FragmentHorrorMoviesBinding
import com.xsoftware.movieapplication.models.Movie
import com.xsoftware.movieapplication.models.MovieResponse
import com.xsoftware.movieapplication.services.MovieApiInterface
import com.xsoftware.movieapplication.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HorrorMoviesFragment : Fragment() ,MovieAdapter.OnItemClickListener{
    private lateinit var binding: FragmentHorrorMoviesBinding
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
        binding = FragmentHorrorMoviesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvPopularHorrorMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvPopularHorrorMovies.setHasFixedSize(true)

        binding.rvUpcomingHorrorMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvUpcomingHorrorMovies.setHasFixedSize(true)

        binding.rvTopRatedHorrorMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTopRatedHorrorMovies.setHasFixedSize(true)

        binding.rvTurkishHorrorMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTurkishHorrorMovies.setHasFixedSize(true)

        /*

        binding.rvActionMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvComedyMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvHorrorMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.rvTurkishMovies.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)

         */


        /*

        binding.rvHorrorMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && firstVisibleItemPosition + visibleItemCount >= totalItemCount && firstVisibleItemPosition >= 0) {
                    if (currentHorrorPage < totalHorrorPage) {
                        currentHorrorPage++
                        loadMoreHorrorMovies()
                    }
                }
            }
        })

        binding.rvTurkishMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && firstVisibleItemPosition + visibleItemCount >= totalItemCount && firstVisibleItemPosition >= 0) {
                    if (currentTurkishPage < totalTurkishPage) {
                        currentTurkishPage++
                        loadMoreTurkishMovies()
                    }
                }
            }
        })

        binding.rvComedyMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && firstVisibleItemPosition + visibleItemCount >= totalItemCount && firstVisibleItemPosition >= 0) {
                    if (currentComedyPage < totalComedyPage) {
                        currentComedyPage++
                        loadMoreComedyMovies()
                    }
                }
            }
        })

        binding.rvActionMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && firstVisibleItemPosition + visibleItemCount >= totalItemCount && firstVisibleItemPosition >= 0) {
                    if (currentActionPage < totalActionPage) {
                        currentActionPage++
                        loadMoreActionMovies()
                    }
                }
            }
        })
        /*


         */
         */






        getBothMovieData()
        // getGenreMovieData()
    }

    private fun getBothMovieData() {
        getPopularHorrorMovies { movies ->
            binding.rvPopularHorrorMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getUpComingHorrorMovies { movies ->
            binding.rvUpcomingHorrorMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getTopRatedHorrorMovies { movies ->
            binding.rvTopRatedHorrorMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
        getTurkishHorrorMovies { movies ->
            binding.rvTurkishHorrorMovies.adapter = MovieAdapter(movies.toMutableList(), this)
        }
    }



    /*
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

     */


    /*
    private fun loadMoreHorrorMovies() {
        isLoading = true
        getHorrorMovies(currentHorrorPage) { movies ->
            (binding.rvHorrorMovies.adapter as MovieAdapter).addMovies(movies)
            isLoading = false
        }
    }

    private fun  loadMoreTurkishMovies(){
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

     */

    private fun getPopularHorrorMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getPopularHorrorMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }
        })
    }

    private fun getTopRatedHorrorMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTopRatedHorrorMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getUpComingHorrorMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getUpComingHorrorMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }

    private fun getTurkishHorrorMovies(callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTurkishHorrorMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
        })
    }


    /*

    private fun getActionMovies(page: Int,callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getActionMovies(page = page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                isLoading = false
            }
        })
    }

    private fun getComedyMovies(page: Int,callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
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
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getHorrorMovies(page = page).enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                isLoading = false
            }
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }
        })
    }

    private fun getTurkishMovies(page:Int,callback: (List<Movie>) -> Unit) {
        val apiService =
            MovieApiService.getInstance(requireContext()).create(MovieApiInterface::class.java)
        apiService.getTurkishMovies(page = page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                isLoading = false
            }
        })
    }

     */
}
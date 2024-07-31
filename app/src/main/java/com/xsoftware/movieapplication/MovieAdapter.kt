package com.xsoftware.movieapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xsoftware.movieapplication.databinding.MovieBannerBinding
import com.xsoftware.movieapplication.models.Movie

class MovieAdapter(
    private val movies: MutableList<Movie>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }

    class MovieViewHolder(private val binding: MovieBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindMovie(movie: Movie, listener: OnItemClickListener) {
            Glide.with(binding.root).load(APIConstants.imageBaseUrl + movie.poster).into(binding.moviePoster)
            binding.root.setOnClickListener { listener.onItemClick(movie) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        if (movie.poster != null) {
            holder.bindMovie(movie, listener)
        } else {
            holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
            holder.itemView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = movies.size

    fun addMovies(newMovies: List<Movie>) {
        val startPosition = movies.size
        movies.addAll(newMovies)
        notifyItemRangeInserted(startPosition, newMovies.size)
    }
}
package com.xsoftware.movieapplication.adapters

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xsoftware.movieapplication.databinding.MovieBannerBinding
import com.xsoftware.movieapplication.models.Series
import android.view.ViewGroup
import com.xsoftware.movieapplication.services.APIConstants

class SeriesAdapter(
    private val series: List<Series>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(series: Series)
    }

    class SeriesViewHolder(private val binding: MovieBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindSeries(series: Series, listener: OnItemClickListener) {
            Glide.with(binding.root).load(APIConstants.imageBaseUrl + series.poster).into(binding.moviePoster)
            binding.root.setOnClickListener { listener.onItemClick(series) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding = MovieBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val series = series[position]
        if (series.poster != null) {
            holder.bindSeries(series, listener)
        } else {
            holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
            holder.itemView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = series.size
}
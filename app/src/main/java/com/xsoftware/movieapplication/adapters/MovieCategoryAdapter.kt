package com.xsoftware.movieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xsoftware.movieapplication.databinding.CategoryItemBinding
import com.xsoftware.movieapplication.models.Genre

class MovieCategoryAdapter(
    private val genres: List<Genre>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MovieCategoryAdapter.CategoryViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(genre: Genre)
    }

    class CategoryViewHolder(private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindCategory(genre: Genre, listener: OnItemClickListener) {
            binding.categoryName.text = genre.name
            binding.root.setOnClickListener { listener.onItemClick(genre) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindCategory(genres[position], listener)
    }

    override fun getItemCount(): Int = genres.size
}
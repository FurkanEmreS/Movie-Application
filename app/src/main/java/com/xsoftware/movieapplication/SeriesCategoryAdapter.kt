package com.xsoftware.movieapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xsoftware.movieapplication.databinding.SeriesCategoryItemBinding
import com.xsoftware.movieapplication.models.Genre

class SeriesCategoryAdapter(
    private val genres: List<Genre>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<SeriesCategoryAdapter.CategoryViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(genre: Genre)
    }

    class CategoryViewHolder(private val binding: SeriesCategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindCategory(genre: Genre, listener: OnItemClickListener) {
            binding.categoryName.text = genre.name
            binding.root.setOnClickListener { listener.onItemClick(genre) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = SeriesCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindCategory(genres[position], listener)
    }

    override fun getItemCount(): Int = genres.size
}
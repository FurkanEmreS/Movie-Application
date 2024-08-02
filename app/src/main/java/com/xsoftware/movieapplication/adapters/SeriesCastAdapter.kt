package com.xsoftware.movieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xsoftware.movieapplication.services.APIConstants
import com.xsoftware.movieapplication.databinding.ProfileCardBinding
import com.xsoftware.movieapplication.models.SeriesCast

class SeriesCastAdapter(
    private val seriesCasts: List<SeriesCast>
) : RecyclerView.Adapter<SeriesCastAdapter.SeriesCastViewHolder>() {

    class SeriesCastViewHolder(private val binding: ProfileCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindCast(cast: SeriesCast) {
            binding.actorName.text = cast.name
            binding.characterName.text = cast.character
            Glide.with(binding.root).load(APIConstants.imageBaseUrl + cast.profilePath).into(binding.profilePicture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesCastViewHolder {
        val binding = ProfileCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesCastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesCastViewHolder, position: Int) {
        holder.bindCast(seriesCasts[position])
    }

    override fun getItemCount(): Int = seriesCasts.size
}

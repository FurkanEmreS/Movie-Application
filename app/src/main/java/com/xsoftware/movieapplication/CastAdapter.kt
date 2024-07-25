package com.xsoftware.movieapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xsoftware.movieapplication.databinding.ProfileCardBinding
import com.xsoftware.movieapplication.models.MovieCast

class CastAdapter(
    private val movieCasts: List<MovieCast>
) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    class CastViewHolder(private val binding: ProfileCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindCast(cast: MovieCast) {
            binding.actorName.text = cast.name
            binding.characterName.text = cast.character
            Glide.with(binding.root).load(APIConstants.imageBaseUrl + cast.profilePath).into(binding.profilePicture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = ProfileCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bindCast(movieCasts[position])
    }

    override fun getItemCount(): Int = movieCasts.size
}

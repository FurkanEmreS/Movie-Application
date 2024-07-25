package com.xsoftware.movieapplication.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCastResponse(
    @SerializedName("cast")
    val movieCasts: List<MovieCast>
) : Parcelable {
    constructor() : this(mutableListOf())
}

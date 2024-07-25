package com.xsoftware.movieapplication.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesCastResponse(
    @SerializedName("cast")
    val seriesCasts: List<SeriesCast>
) : Parcelable {
    constructor() : this(mutableListOf())
}
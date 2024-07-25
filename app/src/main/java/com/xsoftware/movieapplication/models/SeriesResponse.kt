package com.xsoftware.movieapplication.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesResponse(
    @SerializedName("results")
    val series: List<Series>
) : Parcelable {
    constructor() : this(mutableListOf())
}
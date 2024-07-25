package com.xsoftware.movieapplication.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Series(
    @SerializedName("id")
    val id: String?,

    @SerializedName("original_name")
    val name: String?,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("first_air_date")
    val release: String?,

    @SerializedName("vote_average")
    val average: Double?,

    @SerializedName("overview")
    val overview: String?
) : Parcelable {
    constructor() : this("", "", "", "", null, "")
}
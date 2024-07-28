package com.xsoftware.movieapplication.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCast(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("character")
    val character: String?,

    @SerializedName("profile_path")
    val profilePath: String?


) : Parcelable {
    constructor() : this("", "", "", "")
}

package com.xsoftware.movieapplication.services

import com.xsoftware.movieapplication.APIConstants
import com.xsoftware.movieapplication.models.MovieCastResponse
import com.xsoftware.movieapplication.models.MovieResponse
import com.xsoftware.movieapplication.models.SeriesCastResponse
import com.xsoftware.movieapplication.models.SeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_KEY = "api_key=" + APIConstants.apiKey

interface MovieApiInterface {
    @GET("/3/movie/popular?$API_KEY")
    fun getMovieList(): Call<MovieResponse>

    @GET("/3/movie/upcoming?$API_KEY")
    fun getUpcomingMovieList(): Call<MovieResponse>

    @GET("/3/movie/top_rated?$API_KEY")
    fun getTopRatedMovieList(): Call<MovieResponse>

    @GET("/3/movie/now_playing?$API_KEY")
    fun getNowPlayingMovieList(): Call<MovieResponse>

    @GET("/3/movie/{id}/similar?$API_KEY")
    fun getMovieById(
        @Path("id") id: String
    ): Call<MovieResponse>

    @GET("/3/movie/{id}/credits?$API_KEY")
    fun getCastById(
        @Path("id") id: String
    ): Call<MovieCastResponse>

    @GET("/3/tv/popular?$API_KEY")
    fun getPopularSeries(): Call<SeriesResponse>

    @GET("/3/tv/top_rated?$API_KEY")
    fun getTopRatedSeries(): Call<SeriesResponse>

    @GET("/3/tv/on_the_air?$API_KEY")
    fun getOnTheAirSeries(): Call<SeriesResponse>

    @GET("/3/tv/airing_today?$API_KEY")
    fun getAiringTodaySeries(): Call<SeriesResponse>

    @GET("/3/tv/{id}/similar?$API_KEY")
    fun getSeriesById(
        @Path("id") id: String
    ): Call<SeriesResponse>

    @GET("/3/tv/{id}/credits?$API_KEY")
    fun getSeriesCastById(
        @Path("id") id: String
    ): Call<SeriesCastResponse>
}

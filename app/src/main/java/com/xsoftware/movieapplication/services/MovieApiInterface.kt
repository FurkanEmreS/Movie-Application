package com.xsoftware.movieapplication.services

import com.xsoftware.movieapplication.APIConstants
import com.xsoftware.movieapplication.models.MovieCastResponse
import com.xsoftware.movieapplication.models.MovieResponse
import com.xsoftware.movieapplication.models.SeriesCastResponse
import com.xsoftware.movieapplication.models.SeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("/3/discover/movie?$API_KEY")
    fun getActionMovies(
        @Query("with_genres") genreId: Int = 28,  // Aksiyon tür ID'si
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getComedyMovies(
        @Query("with_genres") genreId: Int = 35,  // Komedi tür ID'si
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getHorrorMovies(
        @Query("with_genres") genreId:String ="27",  // Korku tür ID'si
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getTurkishMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_original_language") language: String = "tr",
        @Query("page") page: Int

    ): Call<MovieResponse>



    //Action Movies

    @GET("/3/discover/movie?$API_KEY")
    fun getPopularActiconMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 28,


        ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getTopRatedActionMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 28,

        ): Call<MovieResponse>


    @GET("/3/discover/movie?$API_KEY")
    fun getUpComingActiconMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 28,
        @Query("primary_release_year") year: Int = 2025




        ): Call<MovieResponse>
    @GET("/3/discover/movie?$API_KEY")
    fun getTurkishActionMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 28,
        @Query("with_original_language") language: String = "tr"

        ): Call<MovieResponse>

    //Action Movies



    //Comedy Movies
    @GET("/3/discover/movie?$API_KEY")
    fun getPopularComedyMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 35,


        ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getTopRatedComedyMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 35,

        ): Call<MovieResponse>


    @GET("/3/discover/movie?$API_KEY")
    fun getUpComingComedyMovies(
        @Query("with_genres") genreId: Int = 35,
        @Query("primary_release_year") year: Int = 2025
    ): Call<MovieResponse>



    @GET("/3/discover/movie?$API_KEY")
    fun getTurkishComedyMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 35,
        @Query("with_original_language") language: String = "tr"
    ): Call<MovieResponse>


    //Comedy Movies

    //Horror Movies
    @GET("/3/discover/movie?$API_KEY")
    fun getPopularHorrorMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 27,


        ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getTopRatedHorrorMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 27,

        ): Call<MovieResponse>


    @GET("/3/discover/movie?$API_KEY")
    fun getUpComingHorrorMovies(
        @Query("with_genres") genreId: Int = 27,
        @Query("primary_release_year") year: Int = 2025
    ): Call<MovieResponse>



    @GET("/3/discover/movie?$API_KEY")
    fun getTurkishHorrorMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 27,
        @Query("with_original_language") language: String = "tr"
    ): Call<MovieResponse>



    //Horror Movies
}

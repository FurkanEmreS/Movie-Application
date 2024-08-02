package com.xsoftware.movieapplication.services

import com.xsoftware.movieapplication.models.GenreResponse
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

     //Genre Series
    @GET("/3/genre/tv/list?$API_KEY")
    fun getTvGenres(): Call<GenreResponse>

    @GET("/3/discover/tv?$API_KEY")
    fun getSeriesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1
    ): Call<SeriesResponse>


    @GET("/3/discover/tv?$API_KEY")
    fun getPopularSeriesGenre(
        @Query("with_genres") genreId: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1
    ): Call<SeriesResponse>

    @GET("/3/discover/tv?$API_KEY")
    fun getTopRatedSeriesGenre(
        @Query("with_genres") genreId: Int,
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("page") page: Int = 1
    ): Call<SeriesResponse>

    @GET("/3/discover/tv?$API_KEY")
    fun getUpComingSeriesGenre(
        @Query("with_genres") genreId: Int,
        @Query("sort_by") sortBy: String = "first_air_date.desc",
        @Query("page") page: Int = 1,
    ): Call<SeriesResponse>

    @GET("/3/discover/tv?$API_KEY")
    fun getTurkishSeriesGenre(
        @Query("with_genres") genreId: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1,
        @Query("with_original_language") language: String = "tr"
    ): Call<SeriesResponse>

    @GET("/3/discover/tv?$API_KEY")
    fun getSeriesByGenreAndPage(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Call<SeriesResponse>

//GenreSeries


    //Genre Movies
    @GET("/3/genre/movie/list?$API_KEY")
    fun getMovieGenres(): Call<GenreResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getPopularMoviesGenre(
        @Query("with_genres") genreId: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getTopRatedMoviesGenre(
        @Query("with_genres") genreId: Int,
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getUpcomingMoviesGenre(
        @Query("with_genres") genreId: Int,
        @Query("sort_by") sortBy: String = "release_date.desc",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getMoviesByGenreAndPage(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Call<MovieResponse>




    //Genre Movies

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



    //Turkish Movies
    @GET("/3/discover/movie?$API_KEY")
    fun getPopularTurkishMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_original_language") language: String = "tr"


        ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getTopRatedTurkishMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_original_language") language: String = "tr"

        ): Call<MovieResponse>


    @GET("/3/discover/movie?$API_KEY")
    fun getUpComingTurkishMovies(
        @Query("primary_release_year") year: Int = 2025,
        @Query("with_original_language") language: String = "tr"

    ): Call<MovieResponse>
    //Turkish Movies



    //Science Fiction Movies
    @GET("/3/discover/movie?$API_KEY")
    fun getPopularScienceFictionMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 878,


        ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getTopRatedScienceFictionMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 878,

        ): Call<MovieResponse>


    @GET("/3/discover/movie?$API_KEY")
    fun getUpComingScienceFictionMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 878,
        @Query("primary_release_year") year: Int = 2025




    ): Call<MovieResponse>
    @GET("/3/discover/movie?$API_KEY")
    fun getTurkishScienceFictionMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 878,
        @Query("with_original_language") language: String = "tr"

    ): Call<MovieResponse>
    //Science Fiction Movies




    // Romance Movies
    @GET("/3/discover/movie?$API_KEY")
    fun getPopularRomanceMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 10749,


        ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getTopRatedRomanceMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 10749,

        ): Call<MovieResponse>


    @GET("/3/discover/movie?$API_KEY")
    fun getUpComingRomanceMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 10749,
        @Query("primary_release_year") year: Int = 2025




    ): Call<MovieResponse>
    @GET("/3/discover/movie?$API_KEY")
    fun getTurkishRomanceMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 10749,
        @Query("with_original_language") language: String = "tr"

    ): Call<MovieResponse>
    //Romance Movies



    //Animation Movies
    @GET("/3/discover/movie?$API_KEY")
    fun getPopularAnimationMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 16,


        ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getTopRatedAnimationMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 16,

        ): Call<MovieResponse>


    @GET("/3/discover/movie?$API_KEY")
    fun getUpComingAnimationMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 16,
        @Query("primary_release_year") year: Int = 2025




    ): Call<MovieResponse>
    @GET("/3/discover/movie?$API_KEY")
    fun getTurkishAnimationMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 16,
        @Query("with_original_language") language: String = "tr"

    ): Call<MovieResponse>
    //Animation Movies



    //Crime Movies
    @GET("/3/discover/movie?$API_KEY")
    fun getPopularCrimeMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 80,


        ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getTopRatedCrimeMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 80,

        ): Call<MovieResponse>


    @GET("/3/discover/movie?$API_KEY")
    fun getUpComingCrimeMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 80,
        @Query("primary_release_year") year: Int = 2025




    ): Call<MovieResponse>
    @GET("/3/discover/movie?$API_KEY")
    fun getTurkishCrimeMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 80,
        @Query("with_original_language") language: String = "tr"

    ): Call<MovieResponse>
    //Crime Movies


    //Fantasy Movies

    @GET("/3/discover/movie?$API_KEY")
    fun getPopularFantasyMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 14,


        ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getTopRatedFantasyMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 14

        ): Call<MovieResponse>


    @GET("/3/discover/movie?$API_KEY")
    fun getUpComingFantasyMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 14,
        @Query("primary_release_year") year: Int = 2025




    ): Call<MovieResponse>
    @GET("/3/discover/movie?$API_KEY")
    fun getTurkishFantasyMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 14,
        @Query("with_original_language") language: String = "tr"

    ): Call<MovieResponse>
    //Fantasy Movies

    //Drama Movies

    @GET("/3/discover/movie?$API_KEY")
    fun getPopularDramaMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 18,


        ): Call<MovieResponse>

    @GET("/3/discover/movie?$API_KEY")
    fun getTopRatedDramaMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 18,

        ): Call<MovieResponse>


    @GET("/3/discover/movie?$API_KEY")
    fun getUpComingDramaMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("with_genres") genreId: Int = 18,
        @Query("primary_release_year") year: Int = 2025




    ): Call<MovieResponse>
    @GET("/3/discover/movie?$API_KEY")
    fun getTurkishDramaMovies(
        @Query("sort_by") sortBy: String = "vote_count.desc",
        @Query("with_genres") genreId: Int = 18,
        @Query("with_original_language") language: String = "tr"

    ): Call<MovieResponse>





    //Drama Movies









}

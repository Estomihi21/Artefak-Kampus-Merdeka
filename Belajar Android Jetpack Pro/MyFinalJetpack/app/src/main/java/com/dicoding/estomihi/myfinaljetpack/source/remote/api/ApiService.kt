package com.dicoding.estomihi.myfinaljetpack.source.remote.api

import com.dicoding.estomihi.myfinaljetpack.BuildConfig
import com.dicoding.estomihi.myfinaljetpack.source.remote.response.MovieResponse
import com.dicoding.estomihi.myfinaljetpack.source.remote.response.Response
import com.dicoding.estomihi.myfinaljetpack.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun getMoviesNowPlaying(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Call<Response<MovieResponse>>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Call<MovieResponse>

    @GET("tv/on_the_air")
    fun getTvShowsOnTheAir(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Call<Response<TvShowResponse>>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ) : Call<TvShowResponse>

}
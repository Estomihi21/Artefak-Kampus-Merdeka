package com.dicoding.estomihi.mymovies.data.source

import androidx.lifecycle.LiveData
import com.dicoding.estomihi.mymovies.model.MovieEntity

interface ContentDataSource {

    fun getNowPlayingMovies(): LiveData<List<MovieEntity>>

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity>

    fun getOnTheAirTvShow(): LiveData<List<MovieEntity>>

    fun getDetailTvShow(tvShowId: Int): LiveData<MovieEntity>
}
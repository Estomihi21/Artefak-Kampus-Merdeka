package com.dicoding.estomihi.mymovies.detail

import androidx.lifecycle.ViewModel
import com.dicoding.estomihi.mymovies.model.MovieEntity
import com.dicoding.estomihi.mymovies.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovie(movieId : String) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId : String) {
        this.tvShowId = tvShowId
    }

    fun getDetailMovie() : MovieEntity {
        lateinit var movies : MovieEntity
        val moviesEntities = DataDummy.generateDummyMovies()
        for (movieEntity in moviesEntities) {
            if (movieEntity.id == movieId) {
                movies = movieEntity
            }
        }
        return movies
    }

    fun getDetailTvShow() : MovieEntity {
        lateinit var tvShows : MovieEntity
        val tvShowsEntities = DataDummy.generateDummyTvShows()
        for (tvShowEntity in tvShowsEntities) {
            if (tvShowEntity.id == tvShowId) {
                tvShows = tvShowEntity
            }
        }
        return tvShows
    }
}
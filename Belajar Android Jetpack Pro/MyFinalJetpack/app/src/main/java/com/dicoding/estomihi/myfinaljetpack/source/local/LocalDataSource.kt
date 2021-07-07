package com.dicoding.estomihi.myfinaljetpack.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity
import com.dicoding.estomihi.myfinaljetpack.source.local.room.ContentDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val contentDao: ContentDao) {

    fun getListMovie() : DataSource.Factory<Int, MovieEntity> = contentDao.getListMovie()

    fun getListFavoriteMovie() : DataSource.Factory<Int, MovieEntity> = contentDao.getListFavoriteMovie()

    fun getListTvShow() : DataSource.Factory<Int, TvShowEntity> = contentDao.getListTvShow()

    fun getListFavoriteTvShow() : DataSource.Factory<Int, TvShowEntity> = contentDao.getListFavoriteTvShow()

    fun getDetailMovie(movieId: Int) : LiveData<MovieEntity> = contentDao.getDetailMovieById(movieId)

    fun getDetailTvShow(tvShowId: Int) : LiveData<TvShowEntity> = contentDao.getDetailTvShowById(tvShowId)

    fun insertMovie(movies: List<MovieEntity>) = contentDao.insertMovie(movies)

    fun setFavoriteMovie(movie : MovieEntity) {
        movie.isFavorite = !movie.isFavorite
        contentDao.updateMovie(movie)
    }

    fun insertTvShow(tvShows: List<TvShowEntity>) = contentDao.insertTvShow(tvShows)

    fun setFavoriteTvShow(tvShow : TvShowEntity) {
        tvShow.isFavorite = !tvShow.isFavorite
        contentDao.updateTvShow(tvShow)
    }
}
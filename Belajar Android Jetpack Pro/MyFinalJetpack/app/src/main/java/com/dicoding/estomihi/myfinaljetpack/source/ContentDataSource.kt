package com.dicoding.estomihi.myfinaljetpack.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity
import com.dicoding.estomihi.myfinaljetpack.vo.Resource

interface ContentDataSource {

    fun getNowPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity>

    fun getListFavoriteMovie() : LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity)

    fun getOnTheAirTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity>

    fun getListFavoriteTvShow() : LiveData<PagedList<TvShowEntity>>

    fun setFavoriteTvShow(tvShow: TvShowEntity)
}
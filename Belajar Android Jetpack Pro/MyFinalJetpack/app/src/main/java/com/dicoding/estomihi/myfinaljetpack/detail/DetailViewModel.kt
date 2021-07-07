package com.dicoding.estomihi.myfinaljetpack.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpack.source.ContentRepository
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity

class DetailViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getMovieDetail(movieId: Int) : LiveData<MovieEntity> = contentRepository.getDetailMovie(movieId)

    fun getTvShowDetail(tvShowId: Int) : LiveData<TvShowEntity> = contentRepository.getDetailTvShow(tvShowId)

    fun setFavoriteMovie(movie: MovieEntity) {
        contentRepository.setFavoriteMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity) {
        contentRepository.setFavoriteTvShow(tvShow)
    }
}
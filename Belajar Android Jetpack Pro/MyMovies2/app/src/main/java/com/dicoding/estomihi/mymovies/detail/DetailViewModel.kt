package com.dicoding.estomihi.mymovies.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.estomihi.mymovies.data.source.ContentRepository
import com.dicoding.estomihi.mymovies.model.MovieEntity
import com.dicoding.estomihi.mymovies.utils.DataDummy

class DetailViewModel (private val contentRepository: ContentRepository) : ViewModel() {

    fun getMovieDetail(movieId: Int) : LiveData<MovieEntity> = contentRepository.getDetailMovie(movieId)

    fun getTvShowDetail(tvShowId: Int) : LiveData<MovieEntity> = contentRepository.getDetailTvShow(tvShowId)
}
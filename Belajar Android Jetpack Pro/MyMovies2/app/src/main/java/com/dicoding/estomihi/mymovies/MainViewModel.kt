package com.dicoding.estomihi.mymovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.estomihi.mymovies.data.source.ContentRepository
import com.dicoding.estomihi.mymovies.model.MovieEntity


class MainViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getListNowPlayingMovies() : LiveData<List<MovieEntity>> = contentRepository.getNowPlayingMovies()

    fun getListOnTheAirTvShows() : LiveData<List<MovieEntity>> = contentRepository.getOnTheAirTvShow()
}
package com.dicoding.estomihi.mymovies

import androidx.lifecycle.ViewModel
import com.dicoding.estomihi.mymovies.model.MovieEntity
import com.dicoding.estomihi.mymovies.utils.DataDummy

class MainViewModel : ViewModel() {

    fun getListMovie() : List<MovieEntity> = DataDummy.generateDummyMovies()

    fun getListTvShow() : List<MovieEntity> = DataDummy.generateDummyTvShows()
}
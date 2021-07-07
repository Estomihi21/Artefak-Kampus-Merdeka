package com.dicoding.estomihi.myfinaljetpack.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.estomihi.myfinaljetpack.source.ContentRepository
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.MovieEntity
import com.dicoding.estomihi.myfinaljetpack.source.local.entity.TvShowEntity
import com.dicoding.estomihi.myfinaljetpack.vo.Resource
import javax.inject.Inject


class MainViewModel @Inject constructor(private val contentRepository: ContentRepository) : ViewModel() {

    fun getListNowPlayingMovies() : LiveData<Resource<PagedList<MovieEntity>>> = contentRepository.getNowPlayingMovies()

    fun getListOnTheAirTvShows() : LiveData<Resource<PagedList<TvShowEntity>>> = contentRepository.getOnTheAirTvShow()
}